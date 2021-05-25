package com.example.remotejobs.data.source.remote_data

import com.example.remotejobs.data.model.ResponseItem
import kotlinx.coroutines.flow.Flow

interface IApiHelper {

    fun getPositions(): Flow<List<ResponseItem>>

}