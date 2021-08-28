package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chun.anime.databinding.ItemLandscapeBinding
import com.chun.anime.ui.adapter.vh.ViewHolder
import com.chun.domain.model.BaseObj

class LandscapeHozAdapter(context: Context, data: ArrayList<BaseObj>, config: Config) :
    RvAdapter<BaseObj, ItemLandscapeBinding>(
        context,
        data,
        config
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemLandscapeBinding.inflate(inflater, parent, false)).apply {
            binding.root.layoutParams.width = config.width
            binding.root.layoutParams.height = config.height
        }

    override fun onBindViewHolder(holder: ViewHolder<ItemLandscapeBinding>, position: Int) {
        Glide.with(context)
            .load(data[position].imageUrl)
            .into(holder.binding.thumbnail)
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ItemLandscapeBinding {
        TODO("Not yet implemented")
    }
}