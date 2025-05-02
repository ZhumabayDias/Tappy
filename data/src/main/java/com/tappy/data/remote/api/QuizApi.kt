package com.tappy.data.remote.api

import com.tappy.data.remote.dto.QuizApiResponse
import retrofit2.http.GET

interface QuizApi {
    @GET("api.php?amount=10")
    suspend fun getQuestions(): QuizApiResponse
}