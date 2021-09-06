package com.chun.data.remote.typeadapter

import com.chun.domain.model.Aired
import com.google.gson.stream.JsonReader

class AiredTypeAdapter : BaseTypeAdapter<Aired>() {
    override fun initObj() = Aired()

    override fun parse(obj: Aired, reader: JsonReader, name: String) {
        when (name) {
            "from" -> obj.from = reader.nextString()
            "to" -> obj.to = reader.nextString()
            "string" -> obj.string = reader.nextString()
            "prop" -> obj.prop = PropTypeAdapter().read(reader)
            else -> reader.skipValue()
        }
    }
}
