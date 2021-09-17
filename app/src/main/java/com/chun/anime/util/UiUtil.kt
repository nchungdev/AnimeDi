package com.chun.anime.util

import android.content.Context
import android.util.TypedValue
import com.chun.anime.R

object UiUtil {
    private const val LAST_PERCENTAGE_ITEM_WIDTH = 0.075f

    fun getDisplayWidth(context: Context) = context.resources.displayMetrics.widthPixels

    fun getDisplayHeight(context: Context) = context.resources.displayMetrics.heightPixels

    fun calcHozItemWidth(context: Context, spacing: Int, columnCount: Int, percent: Float): Int {
        val width = (getDisplayWidth(context) - spacing * (columnCount + 1)) / columnCount
        return (width - (percent * width)).toInt()
    }

    fun calcHozItemWidth(context: Context, spacing: Int, columnCount: Int) =
        calcHozItemWidth(context, spacing, columnCount, LAST_PERCENTAGE_ITEM_WIDTH)

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
