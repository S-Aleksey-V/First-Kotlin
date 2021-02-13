package me.tolkstudio.firstkotlin.providers

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.ReceiveChannel
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.User

interface RemoteDataProvider {

   suspend fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
   suspend fun getNoteById(id: String): Note
   suspend fun saveNote(note: Note): Note
   suspend fun getCurrentUser(): User?
   suspend fun deleteNote(noteId: String): Note?
}