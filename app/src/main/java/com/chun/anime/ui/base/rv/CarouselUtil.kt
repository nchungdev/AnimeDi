package com.chun.anime.ui.base.rv

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.chun.anime.AnimeApp
import com.chun.anime.R
import com.chun.anime.util.Ratio
import com.chun.anime.util.UiUtil
import kotlin.math.abs


object CarouselUtil {

    private val pageMargin by lazy {
        AnimeApp.instance.resources.getDimensionPixelSize(R.dimen.carousel_page_margin)
    }
    private val pageOffset by lazy {
        3 * pageMargin
    }

    operator fun invoke(viewPager: ViewPager2) {
        viewPager.apply {
            val displayWidth = UiUtil.getDisplayWidth(context)
            layoutParams.width = displayWidth
            layoutParams.height = ((displayWidth - pageOffset * 2) * Ratio.CAROUSEL_RATIO).toInt()
            setPaddingRelative(pageOffset, 0, pageOffset, 0)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).apply {
                clipChildren = false
                clipToPadding = false
                isNestedScrollingEnabled = false
            }
            CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(pageMargin))
                addTransformer { page, position ->
                    val r = 1 - abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                }
                setPageTransformer(this)
            }
        }
    }
}