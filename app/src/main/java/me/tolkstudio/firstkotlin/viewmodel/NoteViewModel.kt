package me.tolkstudio.firstkotlin.viewmodel

import androidx.lifecycle.ViewModel
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.Repository

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}