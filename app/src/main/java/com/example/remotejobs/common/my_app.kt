package com.example.remotejobs.common

import android.app.Application
import com.example.remotejobs.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //setting up koin
        startKoin {
            androidContext(this@MyApp)

            modules(listOf(roomModule,glideModule,networkModule, viewModelModule, apiServiceModule, repoModule))
        }
    }
}