package com.chun.anime.ui.adapter

import androidx.fragment.app.Fragment
import com.chun.anime.R
import com.chun.anime.ui.base.adapter.BaseFragmentStateAdapter
import com.chun.anime.ui.fragment.*

class AnimeFragmentStateAdapter(fragment: Fragment) :
    BaseFragmentStateAdapter(fragment, R.array.anime_tab_titles) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutAnimeFragment()
            1 -> CharacterFragment()
            2 -> PeopleFragment()
            3 -> ReviewsFragment()
            else -> RecommendationsFragment()
        }
    }
}