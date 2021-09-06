package com.chun.data.remote.typeadapter

import com.chun.domain.model.Simple
import com.google.gson.stream.JsonReader

class SimpleTypeAdapter : BaseTypeAdapter<Simple>() {
    override fun initObj(): Simple {
        return Simple()
    }

    override fun parse(obj: Simple, reader: JsonReader, name: String) {
        when (name) {
            "mal_id" -> obj.id = reader.nextInt()
            "type" -> obj.type = reader.nextString()
            "name" -> obj.name = reader.nextString()
            "url" -> obj.url = reader.nextString()
            else -> reader.skipValue()
        }
    }
}