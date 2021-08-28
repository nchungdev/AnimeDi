package com.chun.data.remote.typeadapter

import com.chun.domain.model.Timestamp
import com.google.gson.stream.JsonReader

class TimestampTypeAdapter : BaseTypeAdapter<Timestamp>() {
    override fun initObj() = Timestamp()

    override fun parse(obj: Timestamp, reader: JsonReader, name: String) {
        when (name) {
            "day" -> obj.day = reader.nextInt()
            "month" -> obj.month = reader.nextInt()
            "year" -> obj.year = reader.nextInt()
            else -> reader.skipValue()
        }
    }

}
