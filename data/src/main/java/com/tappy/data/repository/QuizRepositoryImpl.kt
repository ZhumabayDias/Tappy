package com.tappy.data.repository

import com.tappy.domain.model.Quiz
import com.tappy.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizRepositoryImpl : QuizRepository {
    override fun getQuizzes(): Flow<List<Quiz>> = flow {
        emit(emptyList()) // placeholder data
    }

    override suspend fun getQuizById(id: String): Quiz? = null

    override suspend fun submitQuiz(quizId: String, answers: List<Int>): Boolean = true
}