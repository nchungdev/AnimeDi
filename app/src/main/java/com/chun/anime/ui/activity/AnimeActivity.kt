package com.chun.anime.ui.activity

import com.chun.anime.ui.base.activity.SimpleActivity
import com.chun.anime.ui.fragment.AnimeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeActivity : SimpleActivity() {
    override fun provideFragment() = AnimeFragment.newInstance(intent?.getParcelableExtra(EXTRA_DATA)!!)

    companion object {
        const val EXTRA_DATA = "xData"
    }
}