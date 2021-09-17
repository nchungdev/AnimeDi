package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemCarouselBinding
import com.chun.anime.ui.base.adapter.RvAdapter
import com.chun.anime.ui.base.adapter.ViewHolder
import com.chun.anime.util.glide.loadThumbnail
import com.chun.domain.model.Otaku

class CarouselAdapter(
    context: Context,
    private val requestManager: RequestManager,
    data: ArrayList<Otaku>,
    onClick: (View) -> Unit
) :
    RvAdapter<Otaku, ItemCarouselBinding>(context, data, onClick = onClick) {

    override fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemCarouselBinding.inflate(inflater, parent, false)

    override fun updateViewHolder(holder: ViewHolder<ItemCarouselBinding>, position: Int) {
        val otaku = data[position]
        requestManager
            .loadThumbnail(otaku.imageUrl, isLightTheme)
            .into(holder.binding.thumbnail)
        holder.binding.tvDesc.setTitle(otaku.name)
        holder.itemView.tag = otaku
        holder.itemView.setOnClickListener { onClick(it) }
    }
}
