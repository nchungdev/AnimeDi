package com.chun.data.typeadapter

import com.chun.data.util.ParserUtil
import com.chun.domain.model.BaseObj
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException

abstract class BaseObjTypeAdapter<T : BaseObj> : TypeAdapter<T>() {
    override fun write(out: JsonWriter?, value: T?) = Unit

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): T {
        val obj = createObj()
        `in`.beginObject()
        while (`in`.hasNext()) {
            val name = `in`.nextName()
            if (ParserUtil.consumeNextNull(`in`))
                continue
            parseBaseObj(obj, `in`, name)
        }
        `in`.endObject()
        return obj
    }

    abstract fun createObj(): T

    private fun parseBaseObj(obj: T, `in`: JsonReader, name: String) {
        when (name) {
            "mal_id" -> obj.id = `in`.nextInt()
            "title" -> obj.title = `in`.nextString()
            "image_url" -> obj.imageUrl = `in`.nextString()
            "url" -> obj.url = `in`.nextString()
            "rank" -> obj.rank = `in`.nextInt()
            "score" -> obj.score = `in`.nextDouble()
            "members" -> obj.members = `in`.nextInt()
            "start_date" -> obj.startDate = `in`.nextString()
            "end_date" -> obj.endDate = `in`.nextString()
            "type" -> obj.type = `in`.nextString()
            else -> if (!parseNext(obj, `in`, name)) `in`.skipValue()
        }
    }

    protected open fun parseNext(obj: T, reader: JsonReader, name: String): Boolean {
        return false
    }
}