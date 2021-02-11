package me.tolkstudio.firstkotlin.ui

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ExtensionsKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `should return empty list for null`() {
        val result = emptyList<Int>()
        val testData = null

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should return empty list for empty list`() {
        val result = emptyList<Int>()
        val testData = emptyList<Int>()

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should return same list for single not null element list`() {
        val result = listOf<Int>(42)
        val testData = listOf<Int>(42)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should remove duplicate element`() {
        val result = listOf(42)
        val testData = listOf(42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should sort element`() {
        val result = listOf(5, 4, 3, 2, 1)
        val testData = listOf(1, 5, 3, 2, 4)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should remove null element`() {
        val result = listOf(5, 4, 3, 2, 1)
        val testData = listOf(1, 5, 3, 2, 4, null)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should do nothing for sorted list`() {
        val result = listOf(5, 4, 3, 2, 1)
        val testData = listOf(5, 4, 3, 2, 1)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should return sorted list`() {
        val result = listOf(5, 4, 3, 2, 1)
        val testData = listOf(1, 2, 3, 4, 5)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should return sorted list for element with minus`() {
        val result = listOf(-1, -2, -3, -4, -5)
        val testData = listOf(-5, -4, -3, -2, -1)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should return sorted list with duplicate and null elements and minus elements`() {
        val result = listOf(2, 1, 0, -42)
        val testData = listOf(1, 1, 1, null, null, 0, 0, 0, -42, 0, -42, null, 2)

        assertEquals(result, sortDescAndDistinctAndRemoteNulls(testData))
    }

    @Test
    fun `should failed`() {
        val result = listOf(1, 2, 3, 4, 5)
        val testData = listOf(1, 2, 3, 4, 5)

        assertNotEquals("Должен упасть",result, sortDescAndDistinctAndRemoteNulls(testData))
    }

}