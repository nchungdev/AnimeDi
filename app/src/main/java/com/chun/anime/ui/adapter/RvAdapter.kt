package com.chun.anime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chun.anime.ui.adapter.vh.ViewHolder

abstract class RvAdapter<T, VB : ViewBinding>(
    protected val context: Context,
    protected val data: ArrayList<T>,
    protected val config: Config = Config()
) : RecyclerView.Adapter<ViewHolder<VB>>() {

    protected val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(getViewBinding(parent, viewType))

    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): VB

    override fun getItemCount() = data.size
}