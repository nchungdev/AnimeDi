package com.chun.data.remote.typeadapter

import com.chun.domain.model.Anime
import com.google.gson.stream.JsonReader

open class AnimeTypeAdapter : BaseTypeAdapter<Anime>() {
    override fun initObj() = Anime()

    override fun parse(obj: Anime, reader: JsonReader, name: String) {
        when (name) {
            "mal_id" -> obj.id = reader.nextInt()
            "url" -> obj.url = reader.nextString()
            "image_url" -> obj.imageUrl = reader.nextString()
            "trailer_url" -> obj.trailerUrl = reader.nextString()
            "title" -> obj.title = reader.nextString()
            "title_english" -> obj.titleEn = reader.nextString()
            "title_japanese" -> obj.titleJp = reader.nextString()
            "title_synonyms" -> obj.titleSynonyms = parseArray(reader) { it.nextString() }
            "type" -> obj.type = reader.nextString()
            "source" -> obj.source = reader.nextString()
            "episodes" -> obj.episodes = reader.nextInt()
            "status" -> obj.status = reader.nextString()
            "airing" -> obj.airing = reader.nextBoolean()
            "aired" -> obj.aired = AiredTypeAdapter().read(reader)
            "duration" -> obj.duration = reader.nextString()
            "rating" -> obj.rating = reader.nextString()
            "score" -> obj.score = reader.nextDouble()
            "scored_by" -> obj.scoredBy = reader.nextInt()
            "rank" -> obj.rank = reader.nextInt()
            "popularity" -> obj.popularity = reader.nextInt()
            "members" -> obj.members = reader.nextInt()
            "favorites" -> obj.favorites = reader.nextInt()
            "synopsis" -> obj.synopsis = reader.nextString()
            "background" -> obj.background = reader.nextString()
            "premiered" -> obj.premiered = reader.nextString()
            "broadcast" -> obj.broadcast = reader.nextString()
            "related" -> obj.related = RelatedTypeAdapter().read(reader)
            "producers" -> obj.producers = parseArray(reader) { SimpleTypeAdapter().read(reader) }
            "licensors" -> obj.licensors = parseArray(reader) { SimpleTypeAdapter().read(reader) }
            "studios" -> obj.studios = parseArray(reader) { SimpleTypeAdapter().read(reader) }
            "genres" -> obj.genres = parseArray(reader) { SimpleTypeAdapter().read(reader) }
            "opening_themes" -> obj.openingThemes = parseArray(reader) { it.nextString() }
            "ending_themes" -> obj.endingThemes = parseArray(reader) { it.nextString() }
            else -> reader.skipValue()
        }
    }
}