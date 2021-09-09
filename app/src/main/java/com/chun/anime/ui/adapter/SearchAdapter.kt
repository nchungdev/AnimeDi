package com.chun.anime.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.PagingAdapter
import com.chun.anime.ui.base.rv.ViewHolder
import com.chun.anime.util.glide.SimpleRequestListener
import com.chun.anime.util.glide.loadThumbnail
import com.chun.domain.model.Otaku

class SearchAdapter(
    context: Context,
    private val requestManager: RequestManager,
    config: Config = Config(),
    onClick: (View) -> Unit = {},
    onRetry: (View) -> Unit = {}
) :
    PagingAdapter<Otaku>(context, config, onClick, onRetry) {

    override fun provideViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemPortraitBinding.inflate(inflater, parent, false).apply {
            root.layoutParams.width = config.width
            root.layoutParams.height = config.height
        }
    }

    override fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        if (holder.binding is ItemPortraitBinding) {
            val otaku = getItem(position) ?: return
            holder.binding.tvTitle.text = otaku.name
            requestManager
                .loadThumbnail(otaku.imageUrl, isLightTheme)
                .listener(SimpleRequestListener(
                    onSuccess = { holder.binding.tvTitle.isVisible = false },
                    onFailure = { holder.binding.tvTitle.isVisible = true }
                ))
                .into(holder.binding.thumbnail)
            holder.itemView.tag = otaku
            holder.itemView.setOnClickListener { onClick(it) }
        }
    }
}