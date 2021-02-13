package me.tolkstudio.firstkotlin.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.ui.BaseViewModel
import me.tolkstudio.firstkotlin.ui.NoteViewState
import java.lang.Error

class NoteViewModel(val repository: Repository)
    : BaseViewModel<NoteViewState.Data>() {

    private val currentNote: Note?
        get() = getViewState().poll()?.note


    fun saveChanges(note: Note) {
        setData(NoteViewState.Data(note = note))
    }

    fun loadNote(noteId: String) {
        launch {
            try {
                setData(NoteViewState.Data(note = repository.getNoteById(noteId)))
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }

    fun deleteNote() {
        launch {
            try {
                currentNote?.let { repository.deleteNote(it.id) }
                setData(NoteViewState.Data(isDeleted = true))
            } catch (e: Throwable) {
                setError(e)
            }
        }
    }

    override fun onCleared() {
        launch {
            currentNote?.let { repository.saveNote(it) }
            super.onCleared()
        }
    }
}