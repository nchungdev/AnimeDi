package com.chun.anime.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chun.anime.databinding.ItemPortraitBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.PagingAdapter
import com.chun.anime.ui.base.rv.ViewHolder
import com.chun.domain.model.Anime

class UpcomingAdapter(
    context: Context,
    config: Config = Config(),
    onClick: (View) -> Unit = {},
    onLongClick: (View) -> Unit = {}
) :
    PagingAdapter<Anime>(context, config, onClick, onLongClick) {

    override fun provideViewBinding(parent: ViewGroup, viewType: Int) =
        ItemPortraitBinding.inflate(inflater, parent, false)

    override fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {

    }
}