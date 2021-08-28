package com.chun.anime.ui.base.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chun.anime.R
import com.chun.anime.util.UiUtil
import com.chun.domain.model.type.Display

abstract class RvAdapter<T, VB : ViewBinding>(
    protected val context: Context,
    protected val data: ArrayList<T>,
    protected val config: Config = Config()
) : RecyclerView.Adapter<ViewHolder<VB>>() {

    var onClick: (View) -> Unit = {}
    var onLongClick: (View) -> Unit = {}

    protected val isLightTheme by lazy {
        UiUtil.isLightTheme(context)
    }

    protected val inflater: LayoutInflater = LayoutInflater.from(context)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        val holder = ViewHolder(provideViewBinding(parent, viewType)).apply {
            itemView.setTag(R.id.tagType, viewType)
        }
        createViewHolder(holder)
        return holder
    }

    final override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        holder.itemView.setTag(R.id.tagPosition, position)
        updateViewHolder(holder, position)
    }

    abstract fun provideViewBinding(parent: ViewGroup, viewType: Int): VB

    open fun createViewHolder(holder: ViewHolder<VB>) = Unit

    abstract fun updateViewHolder(holder: ViewHolder<VB>, position: Int)

    override fun getItemCount() = data.size

    final override fun getItemViewType(position: Int) = itemViewType(position)

    @Display
    open fun itemViewType(position: Int): Int = Display.UNIDENTIFIED

    open fun spanSize(position: Int): Int = 1

    fun getItem(position: Int) = data[position]
}
