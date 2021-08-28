package com.chun.anime.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chun.anime.ui.fragment.SearchResultFragment
import com.chun.domain.model.type.Type

class SearchAcFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchResultFragment.newInstance(Type.ANIME)
            else -> SearchResultFragment.newInstance(Type.MANGA)
        }
    }

    fun getPageTitle(position: Int) = if (position == 0) Type.ANIME.uppercase() else Type.MANGA.uppercase()
}
