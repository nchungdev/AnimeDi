package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.chun.anime.R
import com.chun.anime.databinding.ItemSquareBinding
import com.chun.anime.ui.base.adapter.RvAdapter
import com.chun.anime.ui.base.adapter.ViewHolder
import com.chun.anime.ui.base.rv.Config
import com.chun.anime.util.glide.BlurPaletteTransformation
import com.chun.domain.model.Otaku
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class GridAdapter(
    context: Context,
    private val requestManager: RequestManager,
    data: ArrayList<Otaku> = ArrayList(),
    config: Config = Config(),
    onClick: (View) -> Unit = {}
) : RvAdapter<Otaku, ItemSquareBinding>(context, data, config, onClick) {
    private val radius by lazy {
        context.resources.getDimensionPixelSize(R.dimen.thumb_round_radius)
    }

    override fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemSquareBinding.inflate(inflater, parent, false).apply {
            root.layoutParams.width = config.width
            root.layoutParams.height = config.height
        }

    override fun updateViewHolder(holder: ViewHolder<ItemSquareBinding>, position: Int) {
        val otaku = data[position]
        holder.itemView.tag = otaku
        holder.binding.tvTitle.text = otaku.name
        holder.binding.tvScore.text = context.getString(R.string.score_format, otaku.score)
        holder.binding.tvType.text = otaku.type
        holder.itemView.setOnClickListener { onClick(it) }
        val options = RequestOptions.bitmapTransform(
            MultiTransformation(
                CenterCrop(),
                RoundedCornersTransformation(radius, 0),
                BlurPaletteTransformation()
            )
        ).diskCacheStrategy(DiskCacheStrategy.NONE)
        requestManager
            .load(otaku.imageUrl)
            .apply(options)
            .into(holder.binding.thumbnail)
    }
}