package com.chun.anime.util

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.chun.anime.AnimeApp
import com.chun.anime.R
import com.chun.anime.ui.activity.AnimeActivity
import com.chun.anime.ui.activity.MangaActivity
import com.chun.domain.model.Otaku
import com.chun.domain.model.type.Type

private val transitionName by lazy {
    AnimeApp.instance.getString(R.string.transition_thumbnail)
}

fun Activity.openInfo(view: View, otaku: Otaku) {
    val intent =
        (if (otaku.type == Type.ANIME) Intent(this, AnimeActivity::class.java)
        else Intent(this, MangaActivity::class.java)).apply {
            putExtra(AnimeActivity.EXTRA_DATA, otaku)
        }
    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, transitionName).toBundle())
}
