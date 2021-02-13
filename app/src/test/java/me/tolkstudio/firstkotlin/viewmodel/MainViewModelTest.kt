package me.tolkstudio.firstkotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.model.NoteResult
import me.tolkstudio.firstkotlin.model.Repository
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<Repository>()
    private val noteLiveData = MutableLiveData<NoteResult>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        every { mockRepository.getNotes() } returns noteLiveData
        viewModel = MainViewModel(mockRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `should call getNotes once`() {
        verify(exactly = 1) { mockRepository.getNotes() }
    }

    @Test
    fun `should return error`() {
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever { result = it?.error }
        noteLiveData.value = NoteResult.Error(testData)
        assertEquals(result, testData)
    }

    @Test
    fun `should return Notes`() {
        var result: List<Note>? = null
        val testData = listOf(Note(id = "1"), Note(id = "2"))

        viewModel.getViewState().observeForever { result = it?.data }
        noteLiveData.value = NoteResult.Success(testData)
        assertEquals(testData, result)
    }

    @Test
    fun `should remove observer`() {
        viewModel.onCleared()
        assertFalse(noteLiveData.hasObservers())
    }
}