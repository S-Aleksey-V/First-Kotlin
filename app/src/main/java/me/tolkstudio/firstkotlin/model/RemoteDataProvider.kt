package me.tolkstudio.firstkotlin.model

import androidx.lifecycle.LiveData

interface RemoteDataProvider {

    fun subscribeToAllNores(): LiveData<NoteResult>

    fun getNoteById(id: String): LiveData<NoteResult>

    fun saveNote(note: Note): LiveData<NoteResult>
}