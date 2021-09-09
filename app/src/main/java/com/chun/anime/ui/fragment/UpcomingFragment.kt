package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chun.anime.ui.adapter.UpcomingAdapter
import com.chun.anime.ui.base.fragment.LoadingFragment
import com.chun.anime.ui.base.rv.EndlessScrollListener
import com.chun.anime.viewmodel.UpcomingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UpcomingFragment : LoadingFragment() {
    private val viewModel: UpcomingViewModel by viewModels()

    private var adapter: UpcomingAdapter? = null

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        super.bindData(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.upcoming.collect {

            }
        }
        binding.recyclerView.addOnScrollListener(EndlessScrollListener {
            viewModel.accept(UpcomingViewModel.UiAction.Scroll)
        })
    }
}