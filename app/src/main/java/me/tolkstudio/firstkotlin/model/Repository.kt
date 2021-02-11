package me.tolkstudio.firstkotlin.model

import androidx.lifecycle.MutableLiveData
import me.tolkstudio.firstkotlin.providers.FireStoreProvider
import me.tolkstudio.firstkotlin.providers.RemoteDataProvider

class Repository(private val remoteDataProvider: RemoteDataProvider) {

    fun getNotes() = remoteDataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    fun getCurrentUser() = remoteDataProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteDataProvider.deleteNote(noteId)
}