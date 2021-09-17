package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemHeaderBinding
import com.chun.anime.databinding.ItemViewPagerBinding
import com.chun.anime.databinding.NestedRecyclerviewBinding
import com.chun.anime.ui.adapter.item_decoration.MultipleRowSpacingItemDecoration
import com.chun.anime.ui.base.adapter.RvAdapter
import com.chun.anime.ui.base.adapter.ViewHolder
import com.chun.anime.ui.base.rv.CarouselUtil
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.RvUtil
import com.chun.anime.util.Ratio
import com.chun.anime.util.UiUtil
import com.chun.anime.util.get
import com.chun.domain.model.Home
import com.chun.domain.model.Otaku
import com.chun.domain.model.type.ViewType

class HomeAdapter(
    context: Context,
    private val requestManager: RequestManager,
    config: Config,
    onClick: (View) -> Unit
) :
    RvAdapter<Home, ViewBinding>(context, config = config, onClick = onClick) {

    private val types = mutableListOf<Int>()
    private val indices = mutableListOf<Pair<Int, Any>>()

    init {
        anl()
    }

    private fun anl() {
        data.forEachIndexed { index, home ->
            types.add(ViewType.HEADER)
            indices.add(Pair(index, home))
            types.add(home.display.viewType)
            indices.add(Pair(index, home.items))
        }
    }

    override fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewBinding =
        when (viewType) {
            ViewType.HEADER -> ItemHeaderBinding.inflate(inflater, parent, false)
            ViewType.CAROUSEL -> ItemViewPagerBinding.inflate(inflater, parent, false).apply {
                CarouselUtil(viewPager)
            }
            ViewType.SQUARE -> NestedRecyclerviewBinding.inflate(inflater, parent, false).apply {
                recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
                    addItemDecoration(MultipleRowSpacingItemDecoration(context, 2))
                }
            }
            else -> NestedRecyclerviewBinding.inflate(inflater, parent, false).apply {
                RvUtil.onHorizontal(recyclerView)
            }
        }

    override fun getItemCount() = types.size

    override fun itemViewType(position: Int) = types[position]


    override fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        when (getItemViewType(position)) {
            ViewType.CAROUSEL -> {
                val data = indices[position].second as ArrayList<Otaku>
                (holder.binding as ItemViewPagerBinding).apply {
                    if (viewPager.adapter == null) {
                        viewPager.adapter = CarouselAdapter(context, requestManager, data, onClick)
                        viewPager.setCurrentItem(data.size / 2, false)
                    }
                }
            }
            ViewType.PORTRAIT -> {
                (holder.binding as NestedRecyclerviewBinding).recyclerView.apply {
                    val data = indices[position].second as ArrayList<Otaku>
                    adapter = PortraitAdapter(
                        context,
                        requestManager,
                        data,
                        Config(
                            width = UiUtil.calcHozItemWidth(context, config.spacing, 3),
                            ratio = Ratio.PORTRAIT_RATIO
                        ),
                        onClick
                    )
                }
            }
            ViewType.LANDSCAPE -> {
                (holder.binding as NestedRecyclerviewBinding).recyclerView.apply {
                    val data = indices[position].second as ArrayList<Otaku>
                    adapter = LandscapeAdapter(
                        context,
                        requestManager,
                        data,
                        Config(
                            width = UiUtil.calcHozItemWidth(context, config.spacing, 1),
                            ratio = Ratio.LANDSCAPE_RATIO
                        ),
                        onClick
                    )
                }
            }
            ViewType.SQUARE -> {
                (holder.binding as NestedRecyclerviewBinding).recyclerView.apply {
                    val data = indices[position].second as ArrayList<Otaku>
                    adapter = GridAdapter(
                        context,
                        requestManager,
                        data,
                        Config(
                            width = UiUtil.calcHozItemWidth(context, config.spacing, 2),
                            ratio = 1f
                        ),
                        onClick
                    )
                }
            }
            ViewType.HEADER -> (holder.binding as ItemHeaderBinding).apply {
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
        notifyDataSetChanged()
    }

}