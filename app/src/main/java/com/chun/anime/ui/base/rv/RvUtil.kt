package com.chun.anime.ui.base.rv

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.ui.adapter.item_decoration.HozSpacingItemDecoration
import com.chun.anime.ui.adapter.item_decoration.VerticalSpacingItemDecoration

object RvUtil {
    fun onVertical(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpacingItemDecoration(context))
        }
    }

    fun onHorizontal(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(HozSpacingItemDecoration(context))
        }
    }

    fun onHorizontal(recyclerView: RecyclerView, columnCount: Int) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(HozSpacingItemDecoration(context))
        }
    }
}
