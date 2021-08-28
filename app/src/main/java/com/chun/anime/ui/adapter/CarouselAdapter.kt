package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chun.anime.databinding.ItemRecyclerviewBinding
import com.chun.anime.ui.adapter.vh.ViewHolder
import com.chun.domain.model.BaseObj

class CarouselAdapter(context: Context, data: ArrayList<BaseObj>, config: Config) :
    RvAdapter<BaseObj, ItemRecyclerviewBinding>(
        context,
        data,
        config
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRecyclerviewBinding.inflate(inflater, parent, false)).also {
            it.binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder<ItemRecyclerviewBinding>, position: Int) {

    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ItemRecyclerviewBinding {
        TODO("Not yet implemented")
    }
}
