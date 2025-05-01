package com.tappy.app

import com.tappy.domain.model.Question
import com.tappy.domain.model.Quiz

val sampleQuiz = Quiz(
    id = "quiz1",
    title = "Android Basics",
    questions = listOf(
        Question(
            id = "q1",
            questionText = "What is the base language for Android development?",
            options = listOf("Java", "Kotlin", "C++", "Python"),
            correctAnswerIndex = 1
        ),
        Question(
            id = "q2",
            questionText = "What is Jetpack Compose used for?",
            options = listOf("Backend", "Networking", "UI", "Databases"),
            correctAnswerIndex = 2
        )
    )
)