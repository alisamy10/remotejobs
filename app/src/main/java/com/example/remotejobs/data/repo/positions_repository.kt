package com.example.remotejobs.data.repo

import android.util.Log
import com.example.remotejobs.common.INetworkAwareHandler
import com.example.remotejobs.data.model.ResponseItem
import com.example.remotejobs.data.source.local_data.FavouriteDao
import com.example.remotejobs.data.source.local_data.HomeDao
import com.example.remotejobs.data.source.remote_data.IApiHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class PositionsRepository(
    private val apiHelper: IApiHelper,
    private val homeDao: HomeDao,
    private val favouriteDao: FavouriteDao,
    private val networkHandler: INetworkAwareHandler
) {


    private suspend fun getRemoteJobs(): List<ResponseItem> {
        //getCachedJobs()
        val remoteJobsList = mutableListOf<ResponseItem>()
        remoteJobsList.clear()
        apiHelper.getPositions().catch { e ->

        }.collect {
            remoteJobsList.addAll(it)
        }

        return remoteJobsList
    }


    private  fun getCachedJobs(): List<ResponseItem> {


        return homeDao.getAllLocalJobs();
    }


    suspend fun getAll(): List<ResponseItem> {

        // depend on the business of caching

        return if (networkHandler.isOnline()) {
            Log.e("ali", "is online");
            val data = getRemoteJobs()
            homeDao.insertList(data)
            data
        } else {
            Log.e("ali", "is offline");

            getCachedJobs()
        }
    }

    fun isFavorite(articleUrl: String) = favouriteDao.isFavorite(articleUrl)
    
    suspend fun deleteFav(article: ResponseItem) = favouriteDao.deleteFav(article)

    suspend fun insert(article: ResponseItem) = favouriteDao.insert(article)


}