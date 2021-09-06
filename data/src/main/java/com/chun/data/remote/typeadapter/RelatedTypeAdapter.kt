package com.chun.data.remote.typeadapter

import com.chun.domain.model.Related
import com.google.gson.stream.JsonReader

class RelatedTypeAdapter : BaseTypeAdapter<Related>() {
    override fun initObj(): Related {
        return Related()
    }

    override fun parse(obj: Related, reader: JsonReader, name: String) {
        when (name) {
            "Adaptation",
            "Prequel",
            "Side story",
            "Spin-off",
            "Other",
            "Sequel" -> parseArray(reader) { SimpleTypeAdapter().read(it) }
            else -> reader.skipValue()
        }
    }
}
