package com.chun.anime.ui.adapter.item_decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration

class HozSpacingItemDecoration(context: Context) : BaseSpacingItemDecoration(context) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val holder = parent.findContainingViewHolder(view) ?: return
        val itemCount = parent.adapter?.itemCount ?: return
        when (holder.bindingAdapterPosition) {
            0 -> {
                outRect.left = spacing
                outRect.right = spacing / 2
            }
            itemCount - 1 -> {
                outRect.left = spacing / 2
                outRect.right = spacing
            }
            else -> {
                outRect.left = spacing / 2
                outRect.right = spacing / 2
            }
        }
    }
}