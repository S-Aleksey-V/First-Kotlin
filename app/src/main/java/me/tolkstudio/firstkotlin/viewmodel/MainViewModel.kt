package me.tolkstudio.firstkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.ui.MainViewState

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(Repository.getNotes())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}