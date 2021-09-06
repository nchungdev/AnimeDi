package com.chun.domain.model.type

import androidx.annotation.StringDef

@Retention(value = AnnotationRetention.SOURCE)
@StringDef(value = [Type.ANIME, Type.MANGA, Type.PEOPLE])
annotation class Type {
    companion object {
        const val ANIME = "anime"
        const val MANGA = "manga"
        const val PEOPLE = "people"
    }
}