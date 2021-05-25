package com.example.remotejobs.di
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.remotejobs.R
import com.example.remotejobs.data.source.local_data.FovouriteDataBase
import com.example.remotejobs.data.source.local_data.HomeDataBase

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(get(), HomeDataBase::class.java, "Jobs_DATABASE_NAME")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<HomeDataBase>().getHomeDao() }


    single {
        Room.databaseBuilder(get(), FovouriteDataBase::class.java, "Fav_DATABASE_NAME")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { get<FovouriteDataBase>().getFavouriteDao() }


}

val glideModule = module {
    single {
        RequestOptions
            .placeholderOf(R.drawable.ic_global_network)
            .error(R.drawable.ic_global_network)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

    single {
        Glide.with(androidContext())
            .setDefaultRequestOptions(get())

    }

}