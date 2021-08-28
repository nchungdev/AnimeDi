package com.chun.data.util

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import java.io.IOException

object ParserUtil {
    @Throws(IOException::class)
    fun consumeNextNull(`in`: JsonReader): Boolean {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return true
        }
        return false
    }
}