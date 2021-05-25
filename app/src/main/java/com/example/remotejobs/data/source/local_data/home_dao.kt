package com.example.remotejobs.data.source.local_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.remotejobs.data.model.ResponseItem


@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(jobs: List<ResponseItem>): List<Long>


    @Query("DELETE FROM ResponseItem")
    suspend fun deleteAllJobs()


    @Query("SELECT * FROM  ResponseItem")
    fun getAllLocalJobs(): List<ResponseItem>


}