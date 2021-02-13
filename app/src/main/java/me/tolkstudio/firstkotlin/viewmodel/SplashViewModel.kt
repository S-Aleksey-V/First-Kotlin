package me.tolkstudio.firstkotlin.viewmodel

import kotlinx.coroutines.launch
import me.tolkstudio.firstkotlin.model.NoAuthException
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.ui.BaseViewModel
import me.tolkstudio.firstkotlin.ui.SplashViewState

class SplashViewModel(private val repository: Repository) :
        BaseViewModel<Boolean>() {

    fun requestUser() {
        launch {
            repository.getCurrentUser()?.let {
                setData(true)
            } ?: setError(NoAuthException())
        }
    }
}