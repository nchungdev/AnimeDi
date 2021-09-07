package com.chun.domain.model

import android.os.Parcelable
import com.chun.domain.model.type.ObjType
import kotlinx.parcelize.Parcelize

@Parcelize
open class Otaku(
    open var id: Int = 0,
    open var type: String = "",
    var name: String = "",
    open var url: String = "",
    open var imageUrl: String = "",
    open var score: Double = 0.0,
    @ObjType var objType: String = ObjType.UNKNOWN,
    open var genres: List<Simple> = emptyList(),
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