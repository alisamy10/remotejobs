package com.example.remotejobs.data.source.remote_data


import com.example.remotejobs.data.model.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("positions.json")
    suspend fun getRemotePositions(@Query("description")query:String="api" ): List<ResponseItem>
}