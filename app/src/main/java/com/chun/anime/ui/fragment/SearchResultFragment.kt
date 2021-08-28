package com.chun.anime.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chun.anime.R
import com.chun.anime.databinding.FragmentLoadingBinding
import com.chun.anime.ui.adapter.SearchAdapter
import com.chun.anime.ui.adapter.SearchLoadStateAdapter
import com.chun.anime.ui.base.fragment.LoadingFragment
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.util.*
import com.chun.anime.viewmodel.SearchResultViewModel
import com.chun.anime.viewmodel.SearchViewModel
import com.chun.domain.model.Otaku
import com.chun.domain.param.SearchParams
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultFragment : LoadingFragment() {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private val spacing by lazy { resources.getDimensionPixelSize(R.dimen.spacing_normal) }

    private lateinit var currentType: String
    private var searchAdapter: SearchAdapter? = null

    override fun spanCount() = 3

    override fun setupItemDecoration() {
        val context = context ?: return
        binding.recyclerView.addItemDecoration(object : BaseSpacingItemDecoration(context) {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val adapter = searchAdapter ?: return
                val position = parent.getChildAdapterPosition(view)
                setGridSpacing(outRect, position, spanCount() / adapter.spanSize(position))
            }
        })
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        super.setupViews(view, savedInstanceState)

        // bind the state
        binding.bindState(
            uiState = searchResultViewModel.state,
            uiActions = searchResultViewModel.accept
        )
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        currentType = arguments?.get("type") as? String ?: return
    }

    private fun FragmentLoadingBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        searchViewModel.keyword.observe(viewLifecycleOwner) {
            updateRepoListFromInput(it, onQueryChanged)
        }
        lifecycleScope.launch {
            uiState
                .map { it.query }
                .distinctUntilChanged()
                .collect {
                }
        }
    }


    private fun FragmentLoadingBinding.updateRepoListFromInput(
        query: String,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        if (query.trim().isNotEmpty()) {
            recyclerView.scrollToPosition(0)
            onQueryChanged(UiAction.Search(SearchParams().apply {
                type = currentType
                keyword = query
            }))
        }
    }

    private fun FragmentLoadingBinding.bindState(
        uiState: StateFlow<UiState>,
        uiActions: (UiAction) -> Unit
    ) {
        searchAdapter = SearchAdapter(
            requireContext(),
            Glide.with(this@SearchResultFragment),
            Config(
                width = UiUtil.calcItemWidth(requireContext(), spacing, spanCount()),
                ratio = Ratio.PORTRAIT_RATIO
            )
        )
            .apply {
                onClick = {
                    requireActivity().openInfo(it, it.tag as Otaku)
                }
            }
        val searchAdapter = searchAdapter ?: return
        val header = SearchLoadStateAdapter(requireContext()) { searchAdapter.retry() }
        recyclerView.adapter = searchAdapter.withLoadStateHeaderAndFooter(
            header = header,
            footer = SearchLoadStateAdapter(requireContext()) { searchAdapter.retry() }
        )
        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )
        bindList(
            header = header,
            searchAdapter = searchAdapter,
            uiState = uiState,
            onScrollChanged = uiActions
        )
    }

    private fun FragmentLoadingBinding.bindList(
        header: SearchLoadStateAdapter,
        searchAdapter: SearchAdapter,
        uiState: StateFlow<UiState>,
        onScrollChanged: (UiAction.Scroll) -> Unit
    ) {
        error.btnRetry.setOnClickListener { searchAdapter.retry() }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) {
                    onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
                    searchViewModel.toggleKeyboard(false)
                }
            }
        })
        val notLoading = searchAdapter.loadStateFlow
            // Only emit when REFRESH LoadState for RemoteMediator changes.
            .distinctUntilChangedBy { it.refresh }
            // Only react to cases where Remote REFRESH completes i.e., NotLoading.
            .map { it.refresh is LoadState.NotLoading }

        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()

        val shouldScrollToTop = combine(
            notLoading,
            hasNotScrolledForCurrentSearch,
            Boolean::and
        )
            .distinctUntilChanged()

        val pagingData = uiState
            .map { it.pagingData }
            .distinctUntilChanged()

        lifecycleScope.launch {
            combine(shouldScrollToTop, pagingData, ::Pair)
                // Each unique PagingData should be submitted once, take the latest from
                // shouldScrollToTop
                .distinctUntilChangedBy { it.second }
                .collectLatest { (shouldScroll, pagingData) ->
                    searchAdapter.submitData(pagingData)
                    // Scroll only after the data has been submitted to the adapter,
                    // and is a fresh search
                    if (shouldScroll) recyclerView.scrollToPosition(0)
                }
        }

        lifecycleScope.launch {
            searchAdapter.loadStateFlow.collect { loadState ->
                // Show a retry header if there was an error refreshing, and items were previously
                // cached OR default to the default prepend state
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && searchAdapter.itemCount > 0 }
                    ?: loadState.prepend

                val isListEmpty = loadState.refresh is LoadState.NotLoading && searchAdapter.itemCount == 0

                // Only show the list if refresh succeeds, either from the the local db or the remote.
                recyclerView.isVisible =
                    loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
                loading.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.

                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error

                // show empty list
                error.tvTitle.isVisible = isListEmpty || errorState != null
                error.tvEmpty.isVisible = isListEmpty || errorState != null
                error.btnRetry.isVisible = isListEmpty || errorState != null
            }
        }
    }


    companion object {
        fun newInstance(type: String) = SearchResultFragment().apply {
            arguments = bundleOf("type" to type)
        }
    }
}