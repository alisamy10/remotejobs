package com.example.remotejobs.data.sources.favouriteLocalData

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.remotejobs.data.model.ResponseItem
import com.example.remotejobs.data.source.local_data.FavouriteDao
import com.example.remotejobs.data.source.local_data.FovouriteDataBase
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FavouriteNewsDataBaseTest{
    private lateinit var dataDao: FavouriteDao
    private lateinit var db: FovouriteDataBase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FovouriteDataBase::class.java)
            .build()
        dataDao = db.getFavouriteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertFavourite_withSameUrl_returnOne() {
        val article = ResponseItem(

            url = "a",id = "1",company = "Mega" , companyLogo = "",companyUrl = "",createdAt = "",description = ""
                    ,howToApply = "",location = "",title = "",type = "")


        runBlocking {
            dataDao.insert(article)
        }
        val isFavourite =dataDao.isFavorite("1")
        assertThat(isFavourite, Is.`is`(1))

    }
    @Test
    @Throws(Exception::class)
    fun insertFavourite_withDifferentUrl_returnZero() {
        val article = ResponseItem(

            url = "a",id = "1",company = "Mega" , companyLogo = "",companyUrl = "",createdAt = "",description = ""
            ,howToApply = "",location = "",title = "",type = "")
        runBlocking {
            dataDao.insert(article)
        }
        val isFavourite =dataDao.isFavorite("2")
        assertThat(isFavourite, Is.`is`(0))
    }

}