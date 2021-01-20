package me.tolkstudio.firstkotlin.model

object Repository {
    private val notes: List<Note> = listOf(
            Note(
                    "Понедельник",
                    "Первый день",
                    0xFF03DAC5.toInt()
            ),
            Note(
                    "Вторник",
                    "Второй день",
                    0xFF03DAC5.toInt()
            ),
            Note(
                    "Среда",
                    "Третий день",
                    0xFF82E411.toInt()
            ),
            Note(
                    "Четверг",
                    "Четвёртый день день",
                    0xFF82E411.toInt()
            )
    )

    fun getNotes(): List<Note> = notes

}