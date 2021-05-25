package com.example.remotejobs.data.sources.homeCahedData


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.remotejobs.data.model.ResponseItem
import com.example.remotejobs.data.source.local_data.HomeDao
import com.example.remotejobs.data.source.local_data.HomeDataBase
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HomeDataBaseTest{
    private lateinit var dataDao: HomeDao
    private lateinit var db: HomeDataBase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, HomeDataBase::class.java)
            .build()

        dataDao = db.getHomeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {

        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetData() {
        val fakeList= mutableListOf<ResponseItem>().apply {
            add(
                ResponseItem(
                    location = "one",
                    id = "",
                    howToApply = "",
                    createdAt = "",
                    companyUrl = "",
                    companyLogo = "",
                    company = "",
                    description = "",
                    title = "",
                    type ="",
                    url = ""
               )
            )
        }
        runBlocking {
            dataDao.insertList(fakeList)
        }
        val allData = dataDao.getAllLocalJobs()
        assertThat(allData.size, Is.`is`(1))

    }

}