package com.example.remotejobs.data.source.local_data

import androidx.room.*
import com.example.remotejobs.data.model.ResponseItem


@Dao
interface FavouriteDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(job: ResponseItem): Long


    @Delete
    suspend fun deleteFav(job: ResponseItem)


    @Query("SELECT EXISTS (SELECT 1 FROM ResponseItem WHERE id=:id)")
    fun isFavorite(id: String): Int


}