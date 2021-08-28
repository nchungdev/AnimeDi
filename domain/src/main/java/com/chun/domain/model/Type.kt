package com.chun.domain.model

import androidx.annotation.StringDef

@Retention(value = AnnotationRetention.SOURCE)
@StringDef(value = [Type.ANIME, Type.MANGA])
annotation class Type {
    companion object {
        const val ANIME = "anime"
        const val MANGA = "manga"
    }
}