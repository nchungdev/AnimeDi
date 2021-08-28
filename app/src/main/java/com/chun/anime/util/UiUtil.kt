package com.chun.anime.util

import android.content.Context

object UiUtil {
    fun getDisplayWidth(context: Context) = context.resources.displayMetrics.widthPixels

    fun getDisplayHeight(context: Context) = context.resources.displayMetrics.heightPixels

    fun calcHozItemWidth(context: Context, spacing: Int, columnCount: Int, percent: Float) =
        ((getDisplayWidth(context) - spacing * (columnCount + 1)) / (columnCount + percent)).toInt()
}
