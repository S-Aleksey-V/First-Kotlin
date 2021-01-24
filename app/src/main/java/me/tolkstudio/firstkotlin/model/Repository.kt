package me.tolkstudio.firstkotlin.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
            Note(
                    id = UUID.randomUUID().toString(),
                    title = "Понедельник",
                    note = "первый день",
                    color = 0xFF03DAC5.toInt()
            ),
            Note(
                    id = UUID.randomUUID().toString(),
                    title = "Вторник",
                    note = "Второй день",
                    color = 0xFF03DAC5.toInt()
            ),
            Note(
                    id = UUID.randomUUID().toString(),
                    title = "Среда",
                    note = "Третий день",
                    color = 0xFF82E411.toInt()
            ),
            Note(
                    id = UUID.randomUUID().toString(),
                    title = "Четверг",
                    note = "Четвёртый день день",
                    color = 0xFF82E411.toInt()
            )

    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> = notesLiveData

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

}