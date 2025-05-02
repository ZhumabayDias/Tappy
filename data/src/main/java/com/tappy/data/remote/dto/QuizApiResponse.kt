package com.tappy.data.remote.dto

data class QuizApiResponse(
    val results: List<QuizQuestionDto>
)

data class QuizQuestionDto(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)