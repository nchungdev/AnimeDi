package com.chun.data.model

import com.google.gson.annotations.SerializedName

open class RestResult<T> {
    @SerializedName("request_cache_expiry")
    var requestCacheExpiry: Int = 0

    @SerializedName("request_cached")
    var requestCached: Boolean = false

    @SerializedName("request_hash")
    var requestHash: String = ""

    @SerializedName("top", alternate = ["anime"])
    var data: ArrayList<T> = arrayListOf()
}
