package com.chun.domain.model

data class Filter(
    val query: String = "",
    val type: String = "",
    val status: String = "",
    val rated: String = "",
    val genre: String = "",
    val otherBy: String = "",
    val sort: String = ""
)