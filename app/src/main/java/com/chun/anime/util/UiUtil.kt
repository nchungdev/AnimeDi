package com.chun.anime.util

import android.content.Context
import android.util.TypedValue
import com.chun.anime.R

object UiUtil {
    fun getDisplayWidth(context: Context) = context.resources.displayMetrics.widthPixels

    fun getDisplayHeight(context: Context) = context.resources.displayMetrics.heightPixels

    fun calcHozItemWidth(context: Context, spacing: Int, columnCount: Int, percent: Float) =
        ((getDisplayWidth(context) - spacing * (columnCount + 1)) / (columnCount + percent)).toInt()

    fun calcItemWidth(context: Context, spacing: Int, columnCount: Int) =
        (getDisplayWidth(context) - spacing * (columnCount + 1)) / columnCount

    fun isLightTheme(context: Context): Boolean {
        val typeValue = TypedValue()
        return context.theme.resolveAttribute(
            R.attr.isLightTheme,
            typeValue,
            true
        ) && typeValue.data != 0
    }
}
