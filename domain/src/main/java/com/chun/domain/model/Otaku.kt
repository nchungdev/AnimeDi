package com.chun.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Otaku(
    var id: Int = 0,
    var type: String = "",
    var name: String = "",
    var url: String = "",
    var imageUrl: String = "",
    var score: Double = 0.0,
    var objType: String = "",
    var genres: List<Simple> = emptyList(),
) : Parcelable {

    fun toAnime(): Anime {
        val anime = Anime()
        anime.id = id
        anime.type = type
        anime.title = name
        anime.url = url
        anime.imageUrl = imageUrl
        anime.score = score
        anime.imageUrl = imageUrl
        return anime
    }

    fun toManga(): Manga {
        val manga = Manga()
        manga.id = id
        manga.type = type
        manga.title = name
        manga.url = url
        manga.imageUrl = imageUrl
        manga.score = score
        manga.imageUrl = imageUrl
        return manga
    }
}