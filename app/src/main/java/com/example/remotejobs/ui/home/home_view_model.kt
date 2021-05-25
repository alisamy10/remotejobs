package com.example.remotejobs.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotejobs.common.Resource
import com.example.remotejobs.data.model.ResponseItem
import com.example.remotejobs.data.repo.PositionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val jobsRepository: PositionsRepository) : ViewModel() {

    var jobsLiveData = MutableLiveData<Resource<ResponseItem>>()
    var error = MutableLiveData<Boolean>()


    fun getHomeNews() {

        jobsLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("ali", "get data")
            val result = jobsRepository.getAll()
            jobsLiveData.postValue(Resource.Success(result))
            if (result.isNullOrEmpty()) {
                error.postValue(true)
            }
        }

    }

    fun getJobs() = jobsLiveData as LiveData<Resource<ResponseItem>>

    fun saveJob(job: ResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        jobsRepository.insert(job)
    }


    fun deleteJob(job: ResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        jobsRepository.deleteFav(job)
    }

     fun isFavourite(url: String): Int {




        return  jobsRepository.isFavorite(url)
    }
}