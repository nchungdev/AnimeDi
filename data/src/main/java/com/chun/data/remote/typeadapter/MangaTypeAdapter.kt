package com.chun.data.remote.typeadapter

import com.chun.domain.model.Manga
import com.google.gson.stream.JsonReader

class MangaTypeAdapter : BaseTypeAdapter<Manga>() {
    override fun initObj() = Manga()

    override fun parse(obj: Manga, reader: JsonReader, name: String) {
        when (name) {
            "mal_id" -> obj.id = reader.nextInt()
            "url" -> obj.url = reader.nextString()
            "image_url" -> obj.imageUrl = reader.nextString()
            "title" -> obj.title = reader.nextString()
            "title_english" -> obj.titleEn = reader.nextString()
            "title_japanese" -> obj.titleJp = reader.nextString()
            "title_synonyms" -> obj.titleSynonyms = parseArray(reader) { it.nextString() }
            "status" -> obj.status = reader.nextString()
            "type" -> obj.type = reader.nextString()
            "volumes" -> obj.volumes = reader.nextInt()
            "chapters" -> obj.chapters = reader.nextInt()
            "publishing" -> obj.publishing = reader.nextBoolean()
            "published" -> obj.published = PublishedTypeAdapter().read(reader)
            "rank" -> obj.rank = reader.nextInt()
            "score" -> obj.score = reader.nextDouble()
            "scored_by" -> obj.scoredBy = reader.nextInt()
            "popularity" -> obj.popularity = reader.nextInt()
            "members" -> obj.members = reader.nextInt()
            "favorites" -> obj.favorites = reader.nextInt()
            "synopsis" -> obj.synopsis = reader.nextString()
            "background" -> obj.background = reader.nextString()
            "related" -> obj.related = RelatedTypeAdapter().read(reader)
            "genres" -> obj.genres = parseArray(reader) { SimpleTypeAdapter().read(it) }
            "authors" -> obj.authors = parseArray(reader) { SimpleTypeAdapter().read(it) }
            "serializations" -> obj.serializations = parseArray(reader) { SimpleTypeAdapter().read(it) }
            else -> reader.skipValue()
        }
    }
}