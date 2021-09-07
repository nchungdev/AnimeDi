package com.chun.anime.ui.base.rv

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    private var loadMore: Boolean = false
    var visibleThreshold: Int = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (loadMore) return
        val layoutManager = recyclerView.layoutManager ?: return
        layoutManager as LinearLayoutManager
        if (layoutManager.itemCount > 0 && layoutManager.findLastCompletelyVisibleItemPosition() + visibleThreshold >= layoutManager.itemCount) {
            onLoadMore()
            loadMore = true
        }
    }

    fun loadMoreFinished() {
        loadMore = false
    }
}