package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chun.anime.databinding.FragmentSearchAcBinding
import com.chun.anime.ui.adapter.SearchAcFragmentAdapter
import com.chun.anime.ui.base.fragment.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultPagerFragment : BaseFragment<FragmentSearchAcBinding>() {

    override fun setupViews(view: View, savedInstanceState: Bundle?) = Unit

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        val adapter = SearchAcFragmentAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchAcBinding.inflate(inflater, container, false)
}
