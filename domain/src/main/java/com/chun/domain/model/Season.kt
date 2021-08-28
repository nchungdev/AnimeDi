package com.chun.domain.model

data class Season(
    var name: String = "",
    var year: Int = 0,
    var animes: List<Anime> = emptyList()
)