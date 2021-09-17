package com.chun.anime.ui.adapter.item_decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration

class MultipleRowSpacingItemDecoration(context: Context, private val spanCount: Int) :
    BaseSpacingItemDecoration(context) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: return
        val position = parent.getChildAdapterPosition(view)
        outRect.left = if (position < spanCount) spacing else spacing / 2
        outRect.right = if (position + spanCount >= itemCount) spacing else spacing / 2
        outRect.bottom = spacing / 2
        outRect.top = spacing / 2
    }
}
