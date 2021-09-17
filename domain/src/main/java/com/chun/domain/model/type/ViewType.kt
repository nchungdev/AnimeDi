package com.chun.domain.model.type

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(ViewType.UNIDENTIFIED, ViewType.CAROUSEL, ViewType.PORTRAIT, ViewType.LANDSCAPE, ViewType.HEADER)
annotation class ViewType {
    companion object {
        const val CAROUSEL = 0
        const val PORTRAIT = 1
        const val LANDSCAPE = 2
        const val HEADER = 3
        const val UNIDENTIFIED = Int.MIN_VALUE
        const val HOZ_ITEM_LIST = 4
        const val LOAD_MORE = 5
        const val SQUARE = 6
    }
}