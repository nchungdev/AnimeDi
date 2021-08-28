package com.chun.data.remote.typeadapter

import com.chun.data.util.ParserUtil
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

abstract class BaseTypeAdapter<T> : TypeAdapter<T>() {
    override fun write(out: JsonWriter?, value: T) = Unit

    override fun read(`in`: JsonReader): T {
        val obj = initObj()
        `in`.beginObject()
        while (`in`.hasNext()) {
            val name = `in`.nextName()
            if (ParserUtil.consumeNextNull(`in`))
                continue
            parse(obj, `in`, name)
        }
        `in`.endObject()
        return obj
    }

    protected abstract fun initObj(): T

    abstract fun parse(obj: T, reader: JsonReader, name: String)

    protected fun <T> parseArray(`in`: JsonReader, parse: (JsonReader) -> T): List<T> {
        val array = ArrayList<T>()
        `in`.beginArray()
        while (`in`.hasNext()) {
            array.add(parse(`in`))
        }
        return array
    }
}