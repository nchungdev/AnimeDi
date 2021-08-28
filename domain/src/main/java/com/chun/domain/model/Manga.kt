package com.chun.domain.model

data class Manga(
    var id: Int = 0,
    var type: String = "",
    var url: String = "",
    var title: String = "",
    var titleEn: String = "",
    var titleJp: String = "",
    var titleSynonyms: List<String> = emptyList(),
    var status: String = "",
    var imageUrl: String = "",
    var volumes: Int = 0,
    var chapters: Int = 0,
    var publishing: Boolean = false,
    var published: Published = Published(),
    var rank: Int = 0,
    var score: Double = 0.0,
    var scoredBy: Int = 0,
    var popularity: Int = 0,
    var members: Int = 0,
    var favorites: Int = 0,
    var synopsis: String = "",
    var background: String = "",
    var related: Related = Related(),
    var genres: List<Simple> = emptyList(),
    var authors: List<Simple> = emptyList(),
    var serializations: List<Simple> = emptyList()
)