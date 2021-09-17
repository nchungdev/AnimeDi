package com.chun.anime.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.chun.anime.AnimeApp
import com.chun.anime.R
import com.chun.anime.ui.activity.AnimeActivity
import com.chun.anime.ui.activity.MangaActivity
import com.chun.domain.model.Otaku
import com.chun.domain.model.type.ObjType

private val transitionName by lazy {
    AnimeApp.instance.getString(R.string.transition_thumbnail)
}

fun Activity.openInfo(view: View, otaku: Otaku) {
    val intent =
        (if (otaku.type == ObjType.ANIME) Intent(this, AnimeActivity::class.java)
        else Intent(this, MangaActivity::class.java)).apply {
            putExtra(AnimeActivity.EXTRA_DATA, otaku)
        }
    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, transitionName).toBundle())
}

fun Context.openYoutubeLink(url: String) {
    val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        startActivity(intentApp)
    } catch (ex: ActivityNotFoundException) {
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intentBrowser)
    }
}