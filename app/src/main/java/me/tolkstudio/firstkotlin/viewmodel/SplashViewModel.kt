package me.tolkstudio.firstkotlin.viewmodel

import me.tolkstudio.firstkotlin.model.NoAuthException
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.ui.BaseViewModel
import me.tolkstudio.firstkotlin.ui.SplashViewState

class SplashViewModel(private val repository: Repository = Repository) :
        BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())
            SplashViewState()
        }
    }
}