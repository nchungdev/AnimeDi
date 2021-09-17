package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chun.anime.ui.base.adapter.RvAdapter
import com.chun.anime.ui.base.adapter.ViewHolder
import com.chun.anime.ui.base.rv.Config
import com.chun.domain.model.Anime

abstract class AnimeAdapter(
    context: Context,
    data: ArrayList<Anime> = ArrayList(),
    config: Config = Config(),
    onClick: (View) -> Unit = {}
) : RvAdapter<Anime, ViewBinding>(context, data, config, onClick) {


}