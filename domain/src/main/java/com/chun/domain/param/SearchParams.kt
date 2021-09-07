package com.chun.domain.param

import com.chun.domain.model.type.ObjType


data class SearchParams(
    @ObjType val objType: String = ObjType.UNKNOWN,
    private val query: String = "",
    private val type: String = "",
    private val status: String = "",
    private val rated: String = "",
    private val genre: String = "",
    private val otherBy: String = "",
    private val sort: String = ""
) {

    var page: Int = 1

    fun toQueryMap() = mapOf(
        "q" to query,
        "page" to page.toString(),
        "type" to type,
        "status" to status,
        "rated" to rated,
        "genre" to genre,
        "otherBy" to otherBy,
        "sort" to sort,
    )
        .filterNot { it.value.isEmpty() }
}