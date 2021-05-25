package com.example.remotejobs.ui.home

import com.example.remotejobs.data.model.ResponseItem
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test


class SearchUseCase {

    @Test
    fun searchQuery_addFakeList_returnTheSizeOfNewListWithMatchedTitle() {
        val fakeList = mutableListOf<ResponseItem>().apply {
            add(
                ResponseItem(
                    title = "one",
                    company = "one",
                    companyLogo = "",
                    description = "",
                    companyUrl = "",
                    url = "",
                    createdAt = "",
                    howToApply = "",
                    id = ""
                ,location = "",
                    type = ""
                )
            )
            add(
                ResponseItem(
                    title = "two",
                    company = "two",
                    companyLogo = "",
                    description = "",
                    companyUrl = "",
                    url = "",
                    createdAt = "",
                    howToApply = "",
                    id = ""
                    ,location = "",
                    type = ""
                )
            )
            add(
                ResponseItem(
                    title = "three",
                    company = "three",
                    companyLogo = "",
                    description = "",
                    companyUrl = "",
                    url = "",
                    createdAt = "",
                    howToApply = "",
                    id = ""
                    ,location = "",
                    type = ""
                )
            )
            add(
                    ResponseItem(
                        title = "four",
                        company = "four",
                        companyLogo = "",
                        description = "",
                        companyUrl = "",
                        url = "",
                        createdAt = "",
                        howToApply = "",
                        id = ""
                        ,location = "",
                        type = ""
                    )
                )

        }
        val result = searchQuery("o", fakeList)
        assertThat(result?.size, `is`(3))
    }

    @Test
    fun searchQuery_addNullList_returnZeroSize() {
        val result = searchQuery("o", null)
        assertThat(result?.size, `is`(0))
    }

    @Test
    fun searchQuery_addEmptyList_returnZeroSize() {
        val result = searchQuery("o", emptyList())
        assertThat(result?.size, `is`(0))
    }
}