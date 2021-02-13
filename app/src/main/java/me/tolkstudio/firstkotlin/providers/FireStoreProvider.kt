package me.tolkstudio.firstkotlin.providers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import me.tolkstudio.firstkotlin.model.NoAuthException
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"
private val TAG = "${FireStoreProvider::class.java.simpleName} :"

class FireStoreProvider(
        private val firebaseAuth: FirebaseAuth,
        private val db: FirebaseFirestore,
) : RemoteDataProvider {

    private val currentUser
        get() = firebaseAuth.currentUser

    override suspend fun subscribeToAllNotes(): ReceiveChannel<NoteResult> =
            Channel<NoteResult>(Channel.CONFLATED).apply {
                var registration: ListenerRegistration? = null
                try {
                    registration = getUserNotesCollection()
                            .addSnapshotListener { snapshot, e ->
                                val value = e?.let {
                                    NoteResult.Error(it)
                                } ?: snapshot?.let {
                                    val notes = it.documents.map { document ->
                                        document.toObject(Note::class.java)
                                    }
                                    NoteResult.Success(notes)
                                }
                                value?.let { offer(it) }
                            }
                } catch (e: Throwable) {
                    offer(NoteResult.Error(e))
                }

                invokeOnClose { registration?.remove() }
            }

    override suspend fun getNoteById(id: String): Note =
            suspendCoroutine { continuation ->
                try {
                    getUserNotesCollection().document(id).get()
                            .addOnSuccessListener { snapshot ->
                                continuation.resume(snapshot.toObject(Note::class.java)!!)
                            }.addOnFailureListener { exception ->
                                continuation.resumeWithException(exception)
                            }
                } catch (e: Throwable) {
                    continuation.resumeWithException(e)
                }
            }

    override suspend fun saveNote(note: Note): Note =
            suspendCoroutine { continuation ->
                try {
                    getUserNotesCollection().document(note.id)
                            .set(note)
                            .addOnSuccessListener {
                                continuation.resume(note)
                            }.addOnFailureListener {
                                OnFailureListener { exception ->
                                    continuation.resumeWithException(exception)
                                }
                            }
                } catch (e: Throwable) {
                    continuation.resumeWithException(e)
                }
            }

    override suspend fun getCurrentUser(): User? =
            suspendCoroutine { continuation ->
                currentUser?.let {
                    continuation.resume(User(
                            it.displayName ?: "",
                            it.email ?: ""
                    ))
                } ?: continuation.resume(null)
            }

    override suspend fun deleteNote(noteId: String): Note? =
            suspendCoroutine { continuation ->
                try {
                    getUserNotesCollection()
                            .document(noteId)
                            .delete()
                            .addOnSuccessListener {
                                continuation.resume(null)
                            }
                            .addOnFailureListener {
                                continuation.resumeWithException(it)
                            }
                } catch (e: Throwable) {
                    continuation.resumeWithException(e)
                }
            }

    private fun getUserNotesCollection() = currentUser?.let { firebaseUser ->
        db.collection(USERS_COLLECTION)
                .document(firebaseUser.uid)
                .collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()
}