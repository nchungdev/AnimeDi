package com.chun.domain.model.type

import androidx.annotation.StringDef

@Retention(value = AnnotationRetention.SOURCE)
@StringDef(value = [ObjType.ANIME, ObjType.MANGA, ObjType.PEOPLE, ObjType.UNKNOWN])
annotation class ObjType {
    companion object {
        const val ANIME = "anime"
        const val MANGA = "manga"
        const val CHARACTER = "character"
        const val PEOPLE = "people"
        const val UNKNOWN = "unknown"
    }
}