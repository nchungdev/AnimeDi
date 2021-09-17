package com.chun.anime.ui.adapter

import androidx.fragment.app.Fragment
import com.chun.anime.R
import com.chun.anime.ui.base.adapter.BaseFragmentStateAdapter
import com.chun.anime.ui.fragment.HomeFragment

class ExploreFragmentAdapter(fragment: Fragment) : BaseFragmentStateAdapter(fragment, R.array.explore_titles) {
    override fun createFragment(position: Int) =
        when (position) {
            0 -> HomeFragment.newInstance("home_top_section")
            1 -> HomeFragment.newInstance("home_seasonal_section")
            2 -> HomeFragment.newInstance("home_schedule_section")
            else -> HomeFragment.newInstance("home_producer_section")
        }
}