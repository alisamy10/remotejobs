package com.example.remotejobs.api


import com.example.remotejobs.data.model.ResponseItem
import com.example.remotejobs.data.source.remote_data.ApiService

import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ArticlesServiceTest : BaseServiceTest<ApiService>() {

    private lateinit var service: ApiService
    private lateinit var results: List<ResponseItem>


    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchArticlesListTest()   {
        enqueueResponse("/MarvelsResponse.json")
        runBlocking {
            results = requireNotNull(service.getRemotePositions())
        }
        mockWebServer.takeRequest()

        assertThat(results.get(0).company,`is`("SweetRush"))


    }


}
