package com.chun.domain.model

import com.chun.domain.model.type.ObjType

data class Manga(
    override var id: Int = 0,
    override var type: String = "",
    override var url: String = "",
    var title: String = "",
    var titleEn: String = "",
    var titleJp: String = "",
    var titleSynonyms: List<String> = emptyList(),
    var status: String = "",
    override var imageUrl: String = "",
    var volumes: Int = 0,
    var chapters: Int = 0,
    var publishing: Boolean = false,
    var published: Published = Published(),
    var rank: Int = 0,
    override var score: Double = 0.0,
    var scoredBy: Int = 0,
    var popularity: Int = 0,
    var members: Int = 0,
    var favorites: Int = 0,
    var synopsis: String = "",
    var background: String = "",
    var related: Related = Related(),
    override var genres: List<Simple> = emptyList(),
    var authors: List<Simple> = emptyList(),
    var serializations: List<Simple> = emptyList()
) : Otaku(id, type, title, url, imageUrl, score, ObjType.MANGA, genres)