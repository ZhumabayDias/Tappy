package com.tappy.domain.repository

import com.tappy.domain.model.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuizzes(): Flow<List<Quiz>>
    suspend fun getQuizById(id: String): Quiz?
    suspend fun submitQuiz(quizId: String, answers: List<Int>): Boolean
}