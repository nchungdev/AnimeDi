package com.chun.anime.ui.base.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chun.anime.R
import com.chun.anime.util.UiUtil
import com.chun.domain.model.type.ViewType

abstract class RvAdapter<T, VB : ViewBinding>(
    protected val context: Context,
    protected val data: ArrayList<T> = ArrayList(),
    protected val config: Config = Config(),
    protected val onClick: (View) -> Unit = {},
    protected val onLongClick: (View) -> Unit = {},
) : RecyclerView.Adapter<ViewHolder<VB>>() {

    protected val isLightTheme by lazy {
        UiUtil.isLightTheme(context)
    }

    protected val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(provideViewBinding(inflater, parent, viewType)).apply {
            itemView.setTag(R.id.tagType, viewType)
            createViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        holder.itemView.setTag(R.id.tagPosition, position)
        updateViewHolder(holder, position)
    }

    abstract fun provideViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB

    open fun createViewHolder(holder: ViewHolder<VB>) = Unit

    abstract fun updateViewHolder(holder: ViewHolder<VB>, position: Int)

    override fun getItemCount() = data.size

    final override fun getItemViewType(position: Int) = itemViewType(position)

    @ViewType
    open fun itemViewType(position: Int): Int = ViewType.UNIDENTIFIED

    open fun getSpanSize(position: Int): Int = 1

    open fun getItem(position: Int) = data[position]
}
