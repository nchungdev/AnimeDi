package com.chun.anime.ui.base.rv

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)