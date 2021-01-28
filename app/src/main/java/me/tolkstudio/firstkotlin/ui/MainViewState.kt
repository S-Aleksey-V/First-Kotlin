package me.tolkstudio.firstkotlin.ui

import me.tolkstudio.firstkotlin.model.Note

class MainViewState(
        notes: List<Note>? = null, error: Throwable? = null) :
        BaseViewState<List<Note>?>(notes, error)