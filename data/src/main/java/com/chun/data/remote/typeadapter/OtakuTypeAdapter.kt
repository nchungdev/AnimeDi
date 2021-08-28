package com.chun.data.remote.typeadapter

import com.chun.domain.model.Otaku
import com.google.gson.stream.JsonReader

class OtakuTypeAdapter(private val objType: String = "") : BaseTypeAdapter<Otaku>() {
    override fun initObj() = Otaku().also {
        it.objType = objType
    }

    override fun parse(obj: Otaku, reader: JsonReader, name: String) {
        when (name) {
            "mal_id" -> obj.id = reader.nextInt()
            "type" -> obj.type = reader.nextString()
            "title" -> obj.name = reader.nextString()
            "url" -> obj.url = reader.nextString()
            "image_url" -> obj.imageUrl = reader.nextString()
            "score" -> obj.score = reader.nextDouble()
            "genres" -> obj.genres = parseArray(reader) { SimpleTypeAdapter().read(reader) }
            else -> reader.skipValue()
        }
    }
}