package com.chun.anime.util.glide

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.chun.anime.AnimeApp
import com.chun.anime.R

object ColorGenerator {
    private val rounded by lazy { AnimeApp.instance.resources.getDimension(R.dimen.thumb_round_radius) }

    operator fun invoke(): ColorDrawable {
        val rgb = Color.rgb((30..200).random(), (30..200).random(), (30..200).random())
        return RoundedDrawable(rgb, rounded)
    }
}
