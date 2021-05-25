package com.example.remotejobs.ui.home

import com.example.remotejobs.data.model.ResponseItem
import java.util.*
import kotlin.collections.ArrayList

fun searchQuery(newText: String?, responseList: List<ResponseItem>?): MutableList<ResponseItem> {
    val responses: MutableList<ResponseItem> = ArrayList()
    if (responseList == null || responseList.isEmpty())
        return responses
    for (response in responseList) {
        /*
    Useful constant for the root locale. The root locale is the locale whose language, country, and variant are empty ("") strings.
    This is regarded as the base locale of all locales, and is used as the language/country neutral locale for the locale sensitive operations.
     */
        val name: String? = response.title?.toLowerCase(Locale.ROOT)
        if (newText?.toLowerCase(Locale.ROOT)?.let { name?.contains(it) }!!)
            responses.add(response)
    }
    return responses
}