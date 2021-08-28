package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.adapter.vh.ViewHolder
import com.chun.domain.model.BaseObj

class PortraitHozAdapter(context: Context, data: ArrayList<BaseObj>, config: Config) :
    RvAdapter<BaseObj, ItemPortraitBinding>(context, data, config) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPortraitBinding.inflate(inflater, parent, false)).apply {
            binding.thumbnail.layoutParams.width = config.width
            binding.thumbnail.layoutParams.height = config.height
        }

    override fun onBindViewHolder(holder: ViewHolder<ItemPortraitBinding>, position: Int) {
        Glide.with(context)
            .load(data[position].imageUrl)
            .into(holder.binding.thumbnail)
    }

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ItemPortraitBinding {
        TODO("Not yet implemented")
    }
}
