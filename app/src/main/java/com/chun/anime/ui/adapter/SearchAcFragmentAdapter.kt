package com.chun.anime.ui.adapter

import androidx.fragment.app.Fragment
import com.chun.anime.R
import com.chun.anime.ui.base.adapter.BaseFragmentStateAdapter
import com.chun.anime.ui.fragment.SearchResultFragment
import com.chun.domain.model.type.ObjType

class SearchAcFragmentAdapter(fragment: Fragment) : BaseFragmentStateAdapter(fragment, R.array.search_tab_titles) {
    override fun createFragment(position: Int) = when (position) {
        0 -> SearchResultFragment.newInstance(ObjType.ANIME)
        1 -> SearchResultFragment.newInstance(ObjType.MANGA)
        2 -> SearchResultFragment.newInstance(ObjType.CHARACTER)
        else -> SearchResultFragment.newInstance(ObjType.PEOPLE)
    }
}
