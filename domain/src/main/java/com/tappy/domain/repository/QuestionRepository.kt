package com.tappy.domain.repository

import com.tappy.domain.model.Question

interface QuestionRepository {
    suspend fun insertQuestions(questions: List<Question>)
    suspend fun getQuestionsByCategory(category: String): List<Question>
}