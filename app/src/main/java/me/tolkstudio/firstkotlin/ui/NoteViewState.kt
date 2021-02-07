package me.tolkstudio.firstkotlin.ui

import me.tolkstudio.firstkotlin.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)