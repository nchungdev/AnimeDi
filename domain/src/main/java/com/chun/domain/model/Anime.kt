package com.chun.domain.model

class Anime : BaseObj() {
    var episodes: Int = -1
    var airingStart: String = ""
    var synopsis: String = ""
    var genres: List<Genre> = emptyList()
    var licensors: List<String> = emptyList()
    var r18 = false
    var kids = false
    var continuing = false
    var sources = ""
    var producers = arrayListOf<Producer>()
}
