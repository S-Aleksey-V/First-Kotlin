package me.tolkstudio.firstkotlin.model

import androidx.lifecycle.MutableLiveData
import me.tolkstudio.firstkotlin.providers.FireStoreProvider
import me.tolkstudio.firstkotlin.providers.RemoteDataProvider

object Repository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val remoteDataProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteDataProvider.subscribeToAllNores()
    fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    fun getCurrentUser() = remoteDataProvider.getCurrentUser()

}