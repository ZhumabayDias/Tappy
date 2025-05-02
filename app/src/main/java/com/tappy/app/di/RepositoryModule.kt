package com.tappy.app.di

import com.tappy.data.repository.QuizRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { QuizRepository(get()) }}