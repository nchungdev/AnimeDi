package com.chun.domain.model.type

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(Display.UNIDENTIFIED, Display.CAROUSEL, Display.PORTRAIT, Display.LANDSCAPE, Display.HEADER)
annotation class Display {
    companion object {
        const val CAROUSEL = 0
        const val PORTRAIT = 1
        const val LANDSCAPE = 2
        const val HEADER = 3
        const val UNIDENTIFIED = -1
        const val HOZ_ITEM_LIST = 4
        const val LOAD_MORE = 5
    }
}