package com.chun.domain.model

import com.chun.domain.model.type.ObjType

data class Anime(
    override var id: Int = 0,
    override var type: String = "",
    override var url: String = "",
    override var imageUrl: String = "",
    var trailerUrl: String = "",
    var title: String = "",
    var titleEn: String = "",
    var titleJp: String = "",
    var titleSynonyms: List<String> = emptyList(),
    var source: String = "",
    var status: String = "",
    var airing: Boolean = false,
    var aired: Aired = Aired(),
    var background: String = "",
    var broadcast: String = "",
    var duration: String = "",
    var episodes: Int = 0,
    var favorites: Int = 0,
    override var genres: List<Simple> = emptyList(),
    var licensors: List<Simple> = emptyList(),
    var members: Int = 0,
    var popularity: Int = 0,
    var premiered: String = "",
    var producers: List<Simple> = emptyList(),
    var rank: Int = 0,
    var rating: String = "",
    var related: Related = Related(),
    override var score: Double = 0.0,
    var scoredBy: Int = 0,
    var studios: List<Simple> = emptyList(),
    var synopsis: String = "",
    var openingThemes: List<String> = emptyList(),
    var endingThemes: List<String> = emptyList(),
) : Otaku(id, type, title, url, imageUrl, score, ObjType.ANIME, genres)