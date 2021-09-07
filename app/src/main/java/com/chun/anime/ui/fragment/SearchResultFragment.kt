package com.chun.anime.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chun.anime.R
import com.chun.anime.ui.adapter.SearchAdapter
import com.chun.anime.ui.base.fragment.LoadingFragment
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.EndlessScrollListener
import com.chun.anime.util.Ratio
import com.chun.anime.util.UiUtil
import com.chun.anime.util.openInfo
import com.chun.anime.viewmodel.LoadState
import com.chun.anime.viewmodel.SearchResultViewModel
import com.chun.anime.viewmodel.SearchViewModel
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : LoadingFragment() {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private val spacing by lazy { resources.getDimensionPixelSize(R.dimen.spacing_normal) }

    private lateinit var currentType: String
    private val searchAdapter by lazy {
        SearchAdapter(
            requireContext(),
            Glide.with(this@SearchResultFragment),
            Config(
                width = UiUtil.calcItemWidth(requireContext(), spacing, spanCount()),
                ratio = Ratio.PORTRAIT_RATIO,
                spanCount = spanCount()
            ),
            onClick = { v ->
                if (v.id == R.id.btn_retry) {
                    searchResultViewModel.accept(SearchResultViewModel.UiAction.Retry)
                } else {
                    requireActivity().openInfo(v, v.tag as Otaku)
                }
            }
        )
    }

    private val onLoadMoreListener by lazy {
        EndlessScrollListener {
            searchResultViewModel.accept(SearchResultViewModel.UiAction.LoadMore)
        }
    }

    override fun spanCount() = 3

    override fun setupLayoutManager() {
        super.setupLayoutManager()
        with(layoutManager as GridLayoutManager) {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) = searchAdapter.getSpanSize(position)
            }
        }
    }

    override fun setupItemDecoration() {
        val context = context ?: return
        binding.recyclerView.addItemDecoration(object : BaseSpacingItemDecoration(context) {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                when (searchAdapter.getItemViewType(position)) {
                    R.layout.item_retry,
                    R.layout.item_loading -> outRect.bottom = spacing
                    else -> setGridSpacing(outRect, position, spanCount() / searchAdapter.getSpanSize(position))
                }
            }
        })
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        super.setupViews(view, savedInstanceState)
        onLoadMoreListener.visibleThreshold = spanCount()
        binding.recyclerView.addOnScrollListener(onLoadMoreListener)
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    searchViewModel.toggleKeyboard(false)
                }
            }
        })
        binding.recyclerView.adapter = searchAdapter
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        currentType = arguments?.get("type") as? String ?: return
        searchResultViewModel.currentType(currentType)
        searchViewModel.keyword.observe(viewLifecycleOwner) {
            searchResultViewModel.accept(SearchResultViewModel.UiAction.Typing(it))
        }
        searchResultViewModel.state.observe(viewLifecycleOwner) {
            when (val resource = it.data) {
                Resource.Loading -> searchAdapter.showLoading { showLoading() }
                is Resource.Success -> {
                    hideLoading()
                    onLoadMoreListener.loadMoreFinished()
                    searchAdapter.apply {
                        if (it.state == LoadState.APPEND)
                            appendList(resource.data)
                        else if (it.state == LoadState.REFRESH) {
                            showList(resource.data)
                            binding.recyclerView.scrollToPosition(0)
                        }
                    }
                }
                is Resource.Error -> {
                    onLoadMoreListener.loadMoreFinished()
                    searchAdapter.showError { showError() }
                }
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            searchAdapter.clear()
        }
    }

    companion object {
        fun newInstance(type: String) = SearchResultFragment().apply {
            arguments = bundleOf("type" to type)
        }
    }
}