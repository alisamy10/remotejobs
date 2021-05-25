package com.example.remotejobs.di

import com.example.remotejobs.BuildConfig
import com.example.remotejobs.data.source.remote_data.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        GsonConverterFactory.create()
    }
    single {
        CoroutineCallAdapterFactory()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .build()
    }
}

val apiServiceModule = module {
    factory {
        get<Retrofit>().create(ApiService::class.java)
    }

}