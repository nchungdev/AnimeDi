package com.chun.anime.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.chun.anime.R
import com.chun.anime.databinding.FragmentHomeBinding
import com.chun.anime.ui.activity.SearchActivity
import com.chun.anime.ui.adapter.HomeAdapter
import com.chun.anime.ui.base.activity.BaseActivity
import com.chun.anime.ui.base.fragment.BaseFragment
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.RvUtil
import com.chun.anime.util.openInfo
import com.chun.anime.viewmodel.HomeViewModel
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var adapter: HomeAdapter? = null

    private val spacing: Int get() = resources.getDimensionPixelSize(R.dimen.spacing_normal)
    private val navHeight: Int get() = resources.getDimensionPixelSize(R.dimen.nav_height)

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        binding.appBar.addOnOffsetChangedListener(onOffsetChangedListener)
        (activity as BaseActivity<*>).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(context, "Home", Toast.LENGTH_LONG).show()
        }
        binding.content.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.refresh()
        }

        binding.content.recyclerView.apply {
            setPaddingRelative(0, 0, 0, navHeight)
            RvUtil.onVertical(this)
        }

        binding.tvSearch.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        binding.imgSearch.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, null)
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
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
                            HomeAdapter(context, Glide.with(this), arrayListOf(), Config(spacing = spacing)) { view ->
                                (view.tag as? Otaku)?.let {
                                    requireActivity().openInfo(view, it)
                                }
                            }
                        recyclerView.adapter = adapter
                    }
                    adapter?.update(it.data)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun hideLoading() {
        binding.content.loading.progressBar.isVisible = false
    }

    private fun showLoading() {
        binding.content.loading.progressBar.isVisible = true
    }

    private val onOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        val alpha = abs((verticalOffset / appBarLayout.totalScrollRange)).toFloat()
        binding.imgSearch.alpha = alpha
        binding.title.alpha = alpha
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
