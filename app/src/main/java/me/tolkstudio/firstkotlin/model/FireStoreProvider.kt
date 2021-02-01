package me.tolkstudio.firstkotlin.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.*

private const val NOTES_COLLECTION = "notes"
private val TAG = "${FireStoreProvider::class.java.simpleName} :"

class FireStoreProvider : RemoteDataProvider {

    private val db = FirebaseFirestore.getInstance()
    private val notesReference = db.collection(NOTES_COLLECTION)

    override fun subscribeToAllNores(): LiveData<NoteResult> =
            MutableLiveData<NoteResult>().apply {
                notesReference.addSnapshotListener { snapshot, error ->
                    value = error?.let { NoteResult.Error(it) }
                            ?: snapshot?.let { query ->
                                val notes = query.documents.map { document -> document.toObject(Note::class.java) }
                                NoteResult.Success(notes)
                            }
                }
            }

    override fun getNoteById(id: String): LiveData<NoteResult> =
            MutableLiveData<NoteResult>().apply {
                notesReference.document(id).get()
                        .addOnSuccessListener { snapshot ->
                            value = NoteResult.Success(snapshot.toObject(Note::class.java))
                        }.addOnFailureListener { exception ->
                            value = NoteResult.Error(exception)
                        }
            }

    override fun saveNote(note: Note): LiveData<NoteResult> =
            MutableLiveData<NoteResult>().apply {
                notesReference.document(note.id)
                        .set(note)
                        .addOnSuccessListener {
                            Log.d(TAG, "Note $note is saved")
                            value = NoteResult.Success(note)
                        }.addOnFailureListener {
                            OnFailureListener { exception ->
                                Log.d(TAG, "Error saving note $note, message: ${it.message}")
                                value = NoteResult.Error(exception)
                            }
                        }
            }
}