package com.chun.anime.ui.adapter

data class Config(var width: Int = -1, var height: Int = -1, var ratio: Float = 0f) {
    init {
        if (width != -1 && height == -1 && ratio > 0) {
            height = (width * ratio).toInt()
        } else if (width == -1 && height != -1 && ratio > 0) {
            width = (height / ratio).toInt()
        }
    }
}