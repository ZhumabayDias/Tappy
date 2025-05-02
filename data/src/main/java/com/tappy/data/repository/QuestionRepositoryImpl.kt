package com.tappy.data.repository

import com.tappy.data.local.QuestionDao
import com.tappy.data.local.QuestionEntity
import com.tappy.domain.model.Question
import com.tappy.domain.repository.QuestionRepository

class QuestionRepositoryImpl(private val dao: QuestionDao) : QuestionRepository {

    override suspend fun insertQuestions(questions: List<Question>) {
        val entities = questions.map {
            QuestionEntity(
                id = it.id,
                questionText = it.questionText,
                options = it.options,
                correctAnswerIndex = it.correctAnswerIndex,
                category = it.category
            )
        }
        dao.insertAll(entities)
    }

    override suspend fun getQuestionsByCategory(category: String): List<Question> {
        return dao.getByCategory(category).map {
            Question(
                id = it.id,
                questionText = it.questionText,
                options = it.options,
                correctAnswerIndex = it.correctAnswerIndex,
                category = it.category
            )
        }
    }
}