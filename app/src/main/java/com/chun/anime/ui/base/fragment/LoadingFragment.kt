package com.chun.anime.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.databinding.FragmentLoadingBinding
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration

abstract class LoadingFragment : BaseFragment<FragmentLoadingBinding>() {

    protected var layoutManager: LinearLayoutManager? = null

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoadingBinding.inflate(inflater, container, false)

    open fun showLoading() {
        binding.loading.progressBar.isVisible = true
    }

    open fun hideLoading() {
        binding.loading.progressBar.isVisible = false
    }

    open fun showError() {
        hideLoading()
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        setupLayoutManager()
        setupItemDecoration()
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) = Unit

    open fun setupLayoutManager() {
        val spanCount = spanCount()
        layoutManager = if (spanCount > 1) {
            GridLayoutManager(context, spanCount)
        } else {
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        binding.recyclerView.layoutManager = layoutManager
    }

    open fun setupItemDecoration() {
        binding.recyclerView.addItemDecoration(BaseSpacingItemDecoration(context ?: return))
    }

    open fun spanCount() = 1
}