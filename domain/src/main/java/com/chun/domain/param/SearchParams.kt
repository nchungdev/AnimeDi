package com.chun.domain.param

import com.chun.domain.model.type.Type

class SearchParams {
    var type: String = Type.ANIME
    var keyword: String = ""
    var page: Int = 1
    var orderBy: String = ""

    fun toMap() = HashMap<String, String>().apply {
        put("q", keyword)
        put("page", page.toString())
        if (orderBy.isNotEmpty()) {
            put("orderBy", orderBy)
        }
    }

    companion object {
        val EMPTY = SearchParams()
    }
}