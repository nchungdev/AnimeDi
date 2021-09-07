package com.chun.domain.model.type

import androidx.annotation.StringDef

object Subtype {
    @Retention(value = AnnotationRetention.SOURCE)
    @StringDef(value = [AIRING, UPCOMING, TV, MOVIE, OVA, SPECIAL])
    annotation class Anime

    @Retention(value = AnnotationRetention.SOURCE)
    @StringDef(value = [MANGA, NOVELS, ONESHOTS, DOUJIN, MANHWA, MANHUA])
    annotation class Manga

    @Retention(value = AnnotationRetention.SOURCE)
    @StringDef(value = [BY_POPULARITY, FAVORITE])
    annotation class Both

    const val ALL = "all"
    const val AIRING = "airing"
    const val UPCOMING = "upcoming"
    const val TV = "tv"
    const val MOVIE = "movie"
    const val OVA = "ova"
    const val SPECIAL = "special"

    const val MANGA = "manga"
    const val NOVELS = "novels"
    const val ONESHOTS = "oneshots"
    const val DOUJIN = "doujin"
    const val MANHWA = "manhwa"
    const val MANHUA = "manhua"

    const val BY_POPULARITY = "bypopularity"
    const val FAVORITE = "favorite"
}
