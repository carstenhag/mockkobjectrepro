package com.example.mockkobjecttest

import com.emarsys.Emarsys
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Test

class ExampleUnitTest {

    object Object1 {
        fun throwsException() {
            throw Exception("Crash")
        }
    }

    @Test
    fun mocking_isCorrect() {
        val object1 = Object1
        mockkObject(object1)

        try {
            every { object1.throwsException() } returns print("test")
        } catch (e: Exception) {
            print(e)
        }

        object1.throwsException()
    }

    @Test
    fun mockingEmarsys() {
        val emarsysObject = Emarsys
        mockkObject(emarsysObject)

        every { emarsysObject.clearContact() } returns Unit

        emarsysObject.clearContact()

        verify(exactly = 1) { emarsysObject.clearContact() }
    }
}
