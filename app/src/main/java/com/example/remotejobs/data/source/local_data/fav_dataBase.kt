package com.example.remotejobs.data.source.local_data
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remotejobs.data.model.ResponseItem

@Database(entities = [ResponseItem::class], version = 3 , exportSchema = false)

abstract class FovouriteDataBase : RoomDatabase() {
    abstract fun getFavouriteDao(): FavouriteDao
}