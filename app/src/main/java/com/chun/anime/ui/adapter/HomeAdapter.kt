package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.chun.anime.databinding.ItemHeaderBinding
import com.chun.anime.databinding.ItemRecyclerviewBinding
import com.chun.anime.model.Section
import com.chun.anime.ui.adapter.vh.ViewHolder
import com.chun.anime.util.UiUtil
import com.chun.domain.model.BaseObj

class HomeAdapter(context: Context, data: ArrayList<Section>) :
    RvAdapter<Section, ViewBinding>(context, data) {

    private val types = mutableListOf<Int>()
    private val indices = mutableListOf<Pair<Int, Any>>()

    init {
        data.forEachIndexed { index, section ->
            types.add(3)
            indices.add(Pair(index, section.title))
            types.add(section.display.type)
            indices.add(Pair(index, section.items))
        }
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        if (viewType == 3) {
            ItemHeaderBinding.inflate(inflater, parent, false)
        } else
            ItemRecyclerviewBinding.inflate(inflater, parent, false)

    override fun onBindViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        when (getItemViewType(position)) {
//            0 -> {
//                (holder.binding as ItemRecyclerviewBinding).recyclerView.apply {
//                    layoutManager =
//                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                    val second = indices[position].second as List<BaseObj>
//                    adapter = CarouselAdapter(
//                        context, ArrayList(second), Config(
//                            UiUtil.calcHozItemWidth(context, 32, 1, 0.3f)
//                        )
//                    )
//                }
//            }
            0,
            1,
            2 -> {
                (holder.binding as ItemRecyclerviewBinding).recyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    val second = indices[position].second as List<BaseObj>

                    adapter = PortraitHozAdapter(
                        context, ArrayList(second), Config(
                            UiUtil.calcHozItemWidth(context, 32, 3, 0.3f)
                        )
                    )
                }
            }
            3 -> {
                (holder.binding as ItemHeaderBinding).apply {
                    tvTitle.text = indices[position].second as String
                }
            }
        }
    }

    override fun getItemViewType(position: Int) = types[position]

    override fun getItemCount() = types.size
}