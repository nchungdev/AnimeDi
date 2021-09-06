package com.chun.anime.ui.activity

import com.chun.anime.ui.base.activity.SimpleActivity
import com.chun.anime.ui.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : SimpleActivity() {
    override fun provideFragment() = HomeFragment()

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
