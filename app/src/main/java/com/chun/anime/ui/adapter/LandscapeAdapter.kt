package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.ItemLandscapeBinding
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.ui.base.rv.RvAdapter
import com.chun.anime.ui.base.rv.ViewHolder
import com.chun.anime.util.glide.loadBlurBg
import com.chun.anime.util.glide.loadThumbnail
import com.chun.domain.model.Otaku
import com.chun.domain.util.join
import java.util.*

class LandscapeAdapter(
    context: Context,
    private val requestManager: RequestManager,
    data: ArrayList<Otaku>,
    config: Config,
    onClick: (View) -> Unit
) :
    RvAdapter<Otaku, ItemLandscapeBinding>(context, data, config, onClick) {
    override fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemLandscapeBinding.inflate(inflater, parent, false).apply {
            root.layoutParams.width = config.width
            root.layoutParams.height = config.height
        }

    override fun updateViewHolder(holder: ViewHolder<ItemLandscapeBinding>, position: Int) {
        val otaku = getItem(position)
        holder.binding.apply {
            requestManager.loadThumbnail(otaku.imageUrl).into(thumbnail)
            requestManager.loadBlurBg(otaku.imageUrl, isLightTheme, sampling = 15).into(imgBg)
            tvTitle.text = otaku.name
            tvScore.text = "${otaku.score}"
            tvGenres.text = otaku.genres.join()
        }
    }
}
