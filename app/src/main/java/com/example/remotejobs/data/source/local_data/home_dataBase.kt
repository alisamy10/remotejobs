package com.example.remotejobs.data.source.local_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remotejobs.data.model.ResponseItem

@Database(entities = [ResponseItem::class], version = 5 , exportSchema = false)

abstract class HomeDataBase : RoomDatabase() {
    abstract fun getHomeDao(): HomeDao
}