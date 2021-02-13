package me.tolkstudio.firstkotlin.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.ui.BaseViewModel
import me.tolkstudio.firstkotlin.ui.MainViewState

class MainViewModel(val repository: Repository) :
        BaseViewModel<List<Note>?>() {

    private val notesChannel by lazy { runBlocking { repository.getNotes() } }

    init {
        launch {
            notesChannel.consumeEach { result ->
                when (result) {
                    is NoteResult.Success<*> -> setData(result.data as? List<Note>)
                    is NoteResult.Error -> setError(result.error)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        notesChannel.cancel()
        super.onCleared()
    }
}
