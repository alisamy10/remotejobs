package com.example.remotejobs.data.source.remote_data


import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : IApiHelper {


    override fun getPositions() = flow {emit(apiService.getRemotePositions()) }

}