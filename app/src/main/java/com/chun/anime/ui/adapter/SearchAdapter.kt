package com.chun.anime.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.adapter.PagingAdapter
import com.chun.anime.ui.base.adapter.ViewHolder
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
            thumbnail.layoutParams.width = config.width
            thumbnail.layoutParams.height = config.height
        }
    }

    override fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        if (holder.binding is ItemPortraitBinding) {
            val otaku = getItem(position) ?: return
            holder.itemView.tag = otaku
            holder.binding.tvTitle.text = otaku.name
            holder.binding.tvTitleError.text = otaku.name
            holder.binding.tvScore.text = "${otaku.score}"
            holder.binding.tvType.text = otaku.type
            holder.itemView.setOnClickListener { onClick(it) }
            requestManager
                .loadThumbnail(otaku.imageUrl, isLightTheme)
                .listener(SimpleRequestListener(
                    onSuccess = { holder.binding.tvTitleError.isVisible = false },
                    onFailure = { holder.binding.tvTitleError.isVisible = true }
                ))
                .into(holder.binding.thumbnail)
        }
    }
}