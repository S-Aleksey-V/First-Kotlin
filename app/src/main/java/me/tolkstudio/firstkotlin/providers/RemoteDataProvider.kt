package me.tolkstudio.firstkotlin.providers

import androidx.lifecycle.LiveData
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.User

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(noteId: String): LiveData<NoteResult>
}