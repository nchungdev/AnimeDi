package com.chun.anime.ui.base.rv

import android.content.Context
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.R

open class BaseSpacingItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    protected val spacing = context.resources.getDimensionPixelSize(R.dimen.spacing_normal)
    protected val spacingSmall = context.resources.getDimensionPixelSize(R.dimen.spacing_small)
    protected val spacingPrettySmall =
        context.resources.getDimensionPixelSize(R.dimen.spacing_pretty_small)
    protected val spacingLarge = context.resources.getDimensionPixelSize(R.dimen.spacing_pretty_large)

    protected fun setGridSpacing(outRect: Rect, spacing: Int, position: Int, spanCount: Int) {
        val spanIndex = position % spanCount
        outRect.left = spacing - spanIndex * spacing / spanCount
        outRect.right = (spanIndex + 1) * spacing / spanCount
        if (position < spanCount) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }

    protected fun setGridSpacing(outRect: Rect, itemPosition: Int, spanCount: Int) {
        setGridSpacing(outRect, spacing, itemPosition, spanCount)
    }

    protected fun setTopBottomSpacing(outRect: Rect, spacing: Int) {
        outRect.top = spacing
        outRect.bottom = spacing
    }
}
