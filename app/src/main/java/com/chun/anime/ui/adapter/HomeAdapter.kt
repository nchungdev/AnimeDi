package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemHeaderBinding
import com.chun.anime.databinding.ItemRecyclerviewBinding
import com.chun.anime.databinding.ItemViewPagerBinding
import com.chun.anime.ui.base.rv.*
import com.chun.anime.util.Ratio
import com.chun.anime.util.UiUtil
import com.chun.anime.util.get
import com.chun.domain.model.Home
import com.chun.domain.model.Otaku
import com.chun.domain.model.type.Display

class HomeAdapter(context: Context, private val requestManager: RequestManager, data: ArrayList<Home>, config: Config) :
    RvAdapter<Home, ViewBinding>(context, data, config) {

    private val types = mutableListOf<Int>()
    private val indices = mutableListOf<Pair<Int, Any>>()

    init {
        anl()
    }

    private fun anl() {
        data.forEachIndexed { index, home ->
            types.add(Display.HEADER)
            indices.add(Pair(index, home))
            types.add(home.display)
            indices.add(Pair(index, home.items))
        }
    }

    override fun provideViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        when (viewType) {
            Display.HEADER -> ItemHeaderBinding.inflate(inflater, parent, false)
            Display.CAROUSEL -> ItemViewPagerBinding.inflate(inflater, parent, false).apply {
                CarouselUtil(viewPager)
            }
            else -> ItemRecyclerviewBinding.inflate(inflater, parent, false).apply {
                RvUtil.onHorizontal(recyclerView)
            }
        }

    override fun getItemCount() = types.size

    override fun itemViewType(position: Int) = types[position]

    override fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        when (getItemViewType(position)) {
            Display.CAROUSEL -> {
                val data = indices[position].second as ArrayList<Otaku>
                (holder.binding as ItemViewPagerBinding).apply {
                    if (viewPager.adapter == null) {
                        viewPager.adapter = CarouselAdapter(context, requestManager, data).apply {
                            onClick = this@HomeAdapter.onClick
                        }
                        viewPager.setCurrentItem(data.size / 2, false)
                    }
                }
            }
            Display.PORTRAIT -> {
                (holder.binding as ItemRecyclerviewBinding).recyclerView.apply {
                    val data = indices[position].second as ArrayList<Otaku>
                    adapter = PortraitAdapter(
                        context,
                        requestManager,
                        data,
                        Config(
                            width = UiUtil.calcHozItemWidth(context, config.spacing, 3, 0.25f),
                            ratio = Ratio.PORTRAIT_RATIO
                        )
                    ).apply {
                        onClick = this@HomeAdapter.onClick
                    }
                }
            }
            Display.LANDSCAPE -> {
                (holder.binding as ItemRecyclerviewBinding).recyclerView.apply {
                    val data = indices[position].second as ArrayList<Otaku>
                    adapter = LandscapeAdapter(
                        context,
                        requestManager,
                        data,
                        Config(
                            width = UiUtil.calcHozItemWidth(context, config.spacing, 1, 0.25f),
                            ratio = Ratio.LANDSCAPE_RATIO
                        )
                    ).apply {
                        onClick = this@HomeAdapter.onClick
                    }
                }
            }
            Display.HEADER -> (holder.binding as ItemHeaderBinding).apply {
                val home = indices[position].second as Home
                tvTitle.text = home.title.get(context)
                tvSubtitle.text = home.subtitle.get(context)
            }
        }
    }

    fun update(list: List<Home>) {
        types.clear()
        indices.clear()
        data.clear()
        data.addAll(list)
        anl()
    }
}