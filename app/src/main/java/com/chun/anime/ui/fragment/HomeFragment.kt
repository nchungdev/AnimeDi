package com.chun.anime.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.chun.anime.R
import com.chun.anime.databinding.FragmentExploreBinding
import com.chun.anime.ui.activity.SearchActivity
import com.chun.anime.ui.adapter.HomeAdapter
import com.chun.anime.ui.base.fragment.BaseFragment
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.RvUtil
import com.chun.anime.util.openInfo
import com.chun.anime.viewmodel.HomeViewModel
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentExploreBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var adapter: HomeAdapter? = null

    private val spacing: Int get() = resources.getDimensionPixelSize(R.dimen.spacing_normal)
    private val navHeight: Int get() = resources.getDimensionPixelSize(R.dimen.nav_height)

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentExploreBinding.inflate(inflater, container, false)

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        setupToolbar(binding.toolbar)
        binding.searchView.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
            requireActivity().overridePendingTransition(0, 0)
        }
        binding.content.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.refresh()
        }

        binding.content.recyclerView.apply {
            setPaddingRelative(0, 0, 0, navHeight)
            RvUtil.onVertical(this)
        }
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        homeViewModel.initType(arguments?.getString("type") ?: return)
        val recyclerView = binding.content.recyclerView

        homeViewModel.refreshVisible.observe(viewLifecycleOwner) {
            binding.content.swipeRefreshLayout.isRefreshing = it == true
        }

        homeViewModel.homes.observe(viewLifecycleOwner) {
            it ?: return@observe
            when (it) {
                is Resource.Error -> hideLoading()
                Resource.Loading -> showLoading()
                is Resource.Success -> {
                    homeViewModel.toggleRefresh(false)
                    hideLoading()
                    val context = context ?: return@observe
                    if (adapter == null) {
                        adapter =
                            HomeAdapter(context, Glide.with(this), Config(spacing = spacing)) { view ->
                                (view.tag as? Otaku)?.let {
                                    requireActivity().openInfo(view, it)
                                }
                            }
                        recyclerView.adapter = adapter
                    }
                    adapter?.update(it.data)
                }
                Resource.NotLoading -> Unit
            }
        }
    }

    private fun hideLoading() {
        binding.content.loading.progressBar.isVisible = false
    }

    private fun showLoading() {
        binding.content.loading.progressBar.isVisible = true
    }

    companion object {
        fun newInstance(type: String) = HomeFragment().apply {
            arguments = bundleOf("type" to type)
        }
    }
}
