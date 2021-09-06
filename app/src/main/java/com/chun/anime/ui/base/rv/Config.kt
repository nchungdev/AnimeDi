package com.chun.anime.ui.base.rv

import android.view.ViewGroup

class Config(
    ratio: Float = -1f,
    var height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    var width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    val spacing: Int = 0,
    val roundedItem: Boolean = true,
    val columnCount: Int = 1,
) {
    init {
        if (ratio > 0) {
            when {
                width > 0 -> height = (width * ratio).toInt()
                height > 0 -> width = (height / ratio).toInt()
            }
        }
    }
}