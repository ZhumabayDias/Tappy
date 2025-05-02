package com.tappy.data.repository

import com.tappy.data.remote.api.QuizApi
import com.tappy.domain.model.Question

class QuizRepository(private val api: QuizApi) {
    suspend fun getQuestions(): List<Question> {
        val response = api.getQuestions()

        return response.results.map { dto ->
            val allOptions = (dto.incorrect_answers + dto.correct_answer).shuffled()
            Question(
                id = dto.question.take(20),
                questionText = dto.question,
                options = allOptions,
                correctAnswerIndex = allOptions.indexOf(dto.correct_answer),
                category = "Математика"
            )
        }
    }
}