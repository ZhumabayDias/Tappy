package com.tappy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)