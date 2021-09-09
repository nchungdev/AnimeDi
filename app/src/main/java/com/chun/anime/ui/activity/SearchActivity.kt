package com.chun.anime.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import com.chun.anime.databinding.ActivitySearchBinding
import com.chun.anime.ui.base.activity.NavigationActivity
import com.chun.anime.ui.fragment.SearchRecentFragment
import com.chun.anime.ui.fragment.SearchResultPagerFragment
import com.chun.anime.util.SystemUtil
import com.chun.anime.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : NavigationActivity<ActivitySearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    private val runnable by lazy {
        Runnable {
            SystemUtil.toggleKeyword(binding.searchView, true)
        }
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ActivitySearchBinding.inflate(inflater, container, false)

    override fun provideNavigationTags() = arrayOf(
        SearchRecentFragment::class.java.name,
        SearchResultPagerFragment::class.java.name
    )

    override fun setupViews(savedInstanceState: Bundle?) {
        navigate(SearchRecentFragment::class.java.name)
    }

    override fun handleEvent(savedInstanceState: Bundle?) {
        binding.searchView.setOnQueryTextListener(viewModel)
        viewModel.keyboardVisible.observe(this) {
            SystemUtil.toggleKeyword(binding.searchView, it == true)
        }
        viewModel.keyword.observe(this) {
            if (it.isNullOrEmpty()) {
                navigate(SearchRecentFragment::class.java.name)
            } else {
                navigate(SearchResultPagerFragment::class.java.name)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.searchView.postDelayed(runnable, 200)
    }

    override fun onStop() {
        binding.searchView.apply {
            SystemUtil.toggleKeyword(this, false)
            removeCallbacks(runnable)
        }
        super.onStop()
    }

    override fun createFragment(name: String) = when (name) {
        SearchRecentFragment::class.java.name -> SearchRecentFragment()
        SearchResultPagerFragment::class.java.name -> SearchResultPagerFragment()
        else -> throw IllegalStateException("Fragment not defined")
    }
}
