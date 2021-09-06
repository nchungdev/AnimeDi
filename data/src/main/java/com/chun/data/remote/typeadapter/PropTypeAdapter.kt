package com.chun.data.remote.typeadapter

import com.chun.domain.model.Prop
import com.google.gson.stream.JsonReader

class PropTypeAdapter : BaseTypeAdapter<Prop>() {
    override fun initObj() = Prop()

    override fun parse(obj: Prop, reader: JsonReader, name: String) {
        val timestampTypeAdapter = TimestampTypeAdapter()
        when (name) {
            "from" -> obj.from = timestampTypeAdapter.read(reader)
            "to" -> obj.to = timestampTypeAdapter.read(reader)
        }
    }
}