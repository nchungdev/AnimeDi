package com.chun.anime.ui.base.rv

import android.content.Context
import android.view.LayoutInflater
import androidx.paging.LoadStateAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseLoadStateAdapter<VB : ViewBinding>(
    protected val context: Context
) :
    LoadStateAdapter<ViewHolder<VB>>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)
}