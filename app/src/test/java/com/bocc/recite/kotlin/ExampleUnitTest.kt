package com.bocc.recite.kotlin

import com.bocc.recite.kotlin.repository.data.Word
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val word = mock(Word::class.java)
        `when`(word.ps).thenReturn("pp")
        assertEquals(word.ps,"pp")
    }
}
