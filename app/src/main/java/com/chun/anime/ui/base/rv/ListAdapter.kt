package com.chun.anime.ui.base.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ListAdapter<T, VB : ViewBinding> : ListAdapter<T, ViewHolder<VB>> {
    protected val inflater by lazy { LayoutInflater.from(context) }
    protected val context: Context

    protected constructor(context: Context, diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback) {
        this.context = context
    }

    protected constructor(config: AsyncDifferConfig<T>, context: Context) : super(config) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        return ViewHolder(provideVB(inflater, parent, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        bindVH(holder, position)
    }

    abstract fun provideVB(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB

    abstract fun bindVH(holder: RecyclerView.ViewHolder, position: Int)
}