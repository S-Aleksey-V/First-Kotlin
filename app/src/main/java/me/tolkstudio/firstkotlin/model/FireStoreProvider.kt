package me.tolkstudio.firstkotlin.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*

private const val NOTES_COLLECTION = "notes"

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    }

    private val db = FirebaseFirestore.getInstance()
    private val notesReference = db.collection(NOTES_COLLECTION)

    override fun subscribeToAllNores(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.addSnapshotListener { value, error ->
            if (error != null) {
                result.value = NoteResult.Error(error)
            } else if (value != null) {
                val notes = mutableListOf<Note>()

                for (doc: QueryDocumentSnapshot in value) {
                    notes.add(doc.toObject(Note::class.java))
                }
                result.value = NoteResult.Success(notes)
            }
        }

        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    result.value =
                            NoteResult.Success(snapshot.toObject(Note::class.java))
                }
                .addOnFailureListener { exception ->
                    result.value = NoteResult.Error(exception)
                }

        return result
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(note.id)
                .set(note).addOnSuccessListener {
                    Log.d(TAG, "Note $note is saved")
                    result.value = NoteResult.Success(note)
                }.addOnFailureListener {
                    Log.d(TAG, "Error saving note $note, message: ${it.message}")
                    result.value = NoteResult.Error(it)
                }
        return result
    }
}