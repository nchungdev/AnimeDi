package com.chun.anime.ui.base.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BaseFragmentStateAdapter(fragment: Fragment, val titleResId: Int) : FragmentStateAdapter(fragment) {
    protected val titles = fragment.resources.getStringArray(titleResId)

    override fun getItemCount() = titles.size

    fun getPageTitle(position: Int): String = titles[position]
}
