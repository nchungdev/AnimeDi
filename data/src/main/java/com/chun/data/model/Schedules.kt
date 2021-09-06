package com.chun.data.model

import com.google.gson.annotations.SerializedName

open class Schedules<T> {
    @SerializedName("monday", alternate = ["tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"])
    var data: ArrayList<T>? = null
}
