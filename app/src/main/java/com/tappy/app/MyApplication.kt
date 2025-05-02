package com.tappy.app

import android.app.Application
import com.tappy.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.tappy.app.di.networkModule
import com.tappy.app.di.repositoryModule


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, networkModule, repositoryModule)
        }
    }
}