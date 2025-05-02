package com.tappy.app.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.tappy.data.local.AppDatabase
import com.tappy.data.repository.QuestionRepositoryImpl
import com.tappy.domain.repository.QuestionRepository
import com.tappy.domain.usecase.InsertQuestionsUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    // Firebase Auth
    single { FirebaseAuth.getInstance() }

    // Room DB
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "questions.db"
        )
            .fallbackToDestructiveMigration() // ✅ Важно: позволяет пересоздать базу
            .build()
    }

    // DAO
    single { get<AppDatabase>().questionDao() }
    single<QuestionRepository> { QuestionRepositoryImpl(get()) }
    single { InsertQuestionsUseCase(get()) }

}