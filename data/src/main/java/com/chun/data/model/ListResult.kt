package com.chun.data.model

import com.google.gson.annotations.SerializedName

open class ListResult<T> {
    @SerializedName("results", alternate = ["anime", "top"])
    var data: ArrayList<T>? = null
}
