package com.chun.data.typeadapter

import com.chun.domain.model.Anime
import com.google.gson.stream.JsonReader

open class AnimeTypeAdapter : BaseObjTypeAdapter<Anime>() {
    override fun createObj() = Anime()

    override fun parseNext(obj: Anime, reader: JsonReader, name: String): Boolean {
        when (name) {
            "episodes" -> obj.episodes = reader.nextInt()
            "airing_start" -> obj.airingStart = reader.nextString()
            "synopsis" -> obj.synopsis = reader.nextString()
            "genres" -> reader.skipValue()
            "producers" -> reader.skipValue()
            "source" -> reader.nextString()
            "licensors" -> reader.skipValue()
            "r18" -> reader.nextBoolean()
            "kids" -> reader.nextBoolean()
            "continuing" -> reader.nextBoolean()
            else -> reader.skipValue()
        }
        return true
    }
}