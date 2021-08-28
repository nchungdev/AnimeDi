package com.chun.data.remote.typeadapter

import com.chun.domain.model.Published
import com.google.gson.stream.JsonReader

class PublishedTypeAdapter : BaseTypeAdapter<Published>() {
    override fun initObj() = Published()

    override fun parse(obj: Published, reader: JsonReader, name: String) {
        when (name) {
            "from" -> obj.from = reader.nextString()
            "to" -> obj.to = reader.nextString()
            "string" -> obj.string = reader.nextString()
            "prop" -> obj.prop = PropTypeAdapter().read(reader)
            else -> reader.skipValue()
        }
    }
}
