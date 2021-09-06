package com.chun.anime.util

import android.view.View
import androidx.core.view.isVisible
import com.chun.anime.R

const val INVALID = Int.MIN_VALUE

fun View.getTypeTag() = getIntTag(R.id.tagType)

fun View.getPositionTag() = getIntTag(R.id.tagPosition)

fun View.getIntTag(resId: Int): Int {
    val tag = getTag(resId)
    return if (tag is Int) tag else INVALID
}

fun View.show() {
    isVisible = true
}

fun View.hide() {
    isVisible = false
}