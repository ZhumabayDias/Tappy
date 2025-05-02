package com.tappy.domain.usecase

import com.tappy.domain.model.Question
import com.tappy.domain.repository.QuestionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertQuestionsUseCaseTest {

    private lateinit var useCase: InsertQuestionsUseCase
    private lateinit var repository: QuestionRepository

    @Before
    fun setUp() {
        repository = mock()
        useCase = InsertQuestionsUseCase(repository) // ✅ вот здесь мы создаём useCase
    }

    @Test
    fun `insertQuestions should call repository`() = runBlocking {
        val dummyList = listOf(
            Question("1", "Q?", listOf("A", "B", "C"), 0, "Test")
        )

        useCase.invoke(dummyList) // ✅ и теперь он доступен

        verify(repository).insertQuestions(dummyList)
    }
}