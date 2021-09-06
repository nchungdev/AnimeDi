package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.ViewHolder
import com.chun.anime.util.UiUtil
import com.chun.anime.util.glide.SimpleRequestListener
import com.chun.anime.util.glide.loadThumbnail
import com.chun.domain.model.Otaku

class SearchAdapter(
    context: Context,
    private val requestManager: RequestManager,
    private val config: Config
) :
    PagingDataAdapter<Otaku, ViewHolder<ItemPortraitBinding>>(UIMODEL_COMPARATOR) {

    private val inflater = LayoutInflater.from(context)
    private val isLightTheme by lazy { UiUtil.isLightTheme(context) }

    var onClick: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPortraitBinding.inflate(inflater, parent, false)).apply {
            binding.root.layoutParams.width = config.width
            binding.root.layoutParams.height = config.height
        }

    override fun onBindViewHolder(holder: ViewHolder<ItemPortraitBinding>, position: Int) {
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

    fun spanSize(position: Int): Int {
        return 1
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<Otaku>() {
            override fun areItemsTheSame(oldItem: Otaku, newItem: Otaku) =
                oldItem.id == newItem.id && oldItem.objType == newItem.objType

            override fun areContentsTheSame(oldItem: Otaku, newItem: Otaku) = oldItem == newItem
        }
    }
}