package com.tappy.data.local

import androidx.room.*

@Dao
interface QuestionDao {
    @Query("DELETE FROM questions")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE category = :category")
    suspend fun getByCategory(category: String): List<QuestionEntity>

}