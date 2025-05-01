package com.tappy.data.local

import androidx.room.*

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)
}