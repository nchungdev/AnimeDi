package com.chun.anime.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chun.anime.ui.fragment.SearchResultFragment
import com.chun.domain.model.type.ObjType

class SearchAcFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchResultFragment.newInstance(ObjType.ANIME)
            1 -> SearchResultFragment.newInstance(ObjType.MANGA)
            2 -> SearchResultFragment.newInstance(ObjType.CHARACTER)
            else -> SearchResultFragment.newInstance(ObjType.PEOPLE)
        }
    }

    fun getPageTitle(position: Int) = when (position) {
        0 -> ObjType.ANIME.uppercase()
        1 -> ObjType.MANGA.uppercase()
        2 -> ObjType.CHARACTER.uppercase()
        else -> ObjType.PEOPLE.uppercase()
    }
}
