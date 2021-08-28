package com.chun.domain.model

sealed class Subtype {
    object Anime {
        const val ALL = "all"
        const val AIRING = "airing"
        const val UPCOMING = "upcoming"
        const val TV = "tv"
        const val MOVIE = "movie"
        const val OVA = "ova"
        const val SPECIAL = "special"
    }

    object Manga {
        const val ALL = "all-manga"
        const val MANGA = "manga"
        const val NOVELS = "novels"
        const val ONESHOTS = "oneshots"
        const val DOUJIN = "doujin"
        const val MANHWA = "manhwa"
        const val MANHUA = "manhua"
    }

    enum class Both {
        BYPOPULARITY, FAVORITE
    }
}
