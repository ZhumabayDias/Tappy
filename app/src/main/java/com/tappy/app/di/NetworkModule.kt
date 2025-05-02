package com.tappy.app.di

import com.tappy.data.remote.api.QuizApi
import com.tappy.data.remote.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    // 👇 подключаем наш собственный интерсептор
    single { LoggingInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<LoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }
}