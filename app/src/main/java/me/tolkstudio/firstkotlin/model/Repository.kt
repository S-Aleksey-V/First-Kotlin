package me.tolkstudio.firstkotlin.model

import java.util.*

object Repository {
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

    fun getNotes(): List<Note> = notes

}