// ✅ DOMAIN: UseCase для добавления 20 вопросов по категориям
package com.tappy.domain.usecase

import com.tappy.domain.model.Question
import com.tappy.domain.repository.QuestionRepository

class InsertQuestionsUseCase(private val repository: QuestionRepository) {
    suspend operator fun invoke(questions: List<Question>) {
        repository.insertQuestions(questions)
    }
}

