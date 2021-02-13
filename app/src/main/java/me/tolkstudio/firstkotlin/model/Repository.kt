package me.tolkstudio.firstkotlin.model

import androidx.lifecycle.MutableLiveData
import me.tolkstudio.firstkotlin.providers.FireStoreProvider
import me.tolkstudio.firstkotlin.providers.RemoteDataProvider

class Repository(private val remoteDataProvider: RemoteDataProvider) {

    suspend fun getNotes() = remoteDataProvider.subscribeToAllNotes()
    suspend fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    suspend fun getCurrentUser() = remoteDataProvider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = remoteDataProvider.deleteNote(noteId)
}