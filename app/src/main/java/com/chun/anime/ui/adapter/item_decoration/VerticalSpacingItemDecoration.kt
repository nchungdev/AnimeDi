package com.chun.anime.ui.adapter.item_decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.ui.base.rv.BaseSpacingItemDecoration
import com.chun.anime.util.getTypeTag
import com.chun.domain.model.type.ViewType

class VerticalSpacingItemDecoration(context: Context) : BaseSpacingItemDecoration(context) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: return
        val holder = parent.findContainingViewHolder(view) ?: return
        if (holder.bindingAdapterPosition == 0) {
            outRect.top = spacing
        } else if (holder.bindingAdapterPosition == itemCount - 1) {
            outRect.bottom = spacing
        }
        when (view.getTypeTag()) {
            ViewType.HEADER -> {
                outRect.top = spacingPrettySmall
                outRect.bottom = spacingSmall
            }
        }
    }
}
