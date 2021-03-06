package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.RvAdapter
import com.chun.anime.ui.base.rv.ViewHolder
import com.chun.anime.util.glide.SimpleRequestListener
import com.chun.anime.util.glide.loadThumbnail
import com.chun.domain.model.Otaku

class PortraitAdapter(
    context: Context,
    private val requestManager: RequestManager,
    data: ArrayList<Otaku>,
    config: Config,
    onClick: (View) -> Unit
) :
    RvAdapter<Otaku, ItemPortraitBinding>(context, data, config, onClick) {

    override fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemPortraitBinding.inflate(inflater, parent, false).apply {
            thumbnail.layoutParams.width = config.width
            thumbnail.layoutParams.height = config.height
        }

    override fun updateViewHolder(holder: ViewHolder<ItemPortraitBinding>, position: Int) {
        val otaku = data[position]
        holder.itemView.tag = otaku
        holder.binding.tvTitle.text = otaku.name
        holder.itemView.setOnClickListener { onClick(it) }
        requestManager
            .loadThumbnail(otaku.imageUrl, isLightTheme)
            .listener(SimpleRequestListener(
                onSuccess = { holder.binding.tvTitle.isVisible = false },
                onFailure = { holder.binding.tvTitle.isVisible = true }
            ))
            .into(holder.binding.thumbnail)
    }
}
