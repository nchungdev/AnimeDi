package com.chun.anime.ui.base.rv

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chun.anime.R
import com.chun.anime.databinding.ItemLoadingBinding
import com.chun.anime.databinding.ItemRetryBinding
import com.chun.anime.util.UiUtil
import com.chun.anime.util.isValidPosition
import com.chun.domain.model.type.ViewType

abstract class PagingAdapter<T>(
    protected val context: Context,
    protected val config: Config = Config(),
    protected val onClick: (View) -> Unit = {},
    protected val onLongClick: (View) -> Unit = {},
) : RecyclerView.Adapter<ViewHolder<ViewBinding>>() {

    protected val inflater by lazy { LayoutInflater.from(context) }
    protected val isLightTheme by lazy { UiUtil.isLightTheme(context) }
    protected val types by lazy { ArrayList<Int>() }
    protected val indices by lazy { ArrayList<Pair<Int, Any>>() }
    protected val data by lazy { ArrayList<T>() }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewBinding> {
        val holder = ViewHolder(
            when (viewType) {
                R.layout.item_loading -> ItemLoadingBinding.inflate(inflater, parent, false)
                R.layout.item_retry -> ItemRetryBinding.inflate(inflater, parent, false).apply {
                    btnRetry.setOnClickListener(onClick)
                }
                else -> provideViewBinding(parent, viewType)
            }
        )
            .apply {
                itemView.setTag(R.id.tagType, viewType)
            }
        createViewHolder(holder)
        return holder
    }

    final override fun onBindViewHolder(holder: ViewHolder<ViewBinding>, position: Int) {
        holder.itemView.setTag(R.id.tagPosition, position)
        updateViewHolder(holder, position)
    }

    abstract fun provideViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    open fun createViewHolder(holder: ViewHolder<ViewBinding>) = Unit

    abstract fun updateViewHolder(holder: ViewHolder<ViewBinding>, position: Int)

    override fun getItemCount() = types.size

    final override fun getItemViewType(position: Int) =
        if (position.isValidPosition(types)) types[position]
        else itemViewType(position)

    @ViewType
    open fun itemViewType(position: Int): Int = ViewType.UNIDENTIFIED

    fun getSpanSize(position: Int): Int = when (getItemViewType(position)) {
        R.layout.item_retry,
        R.layout.item_loading -> config.spanCount
        else -> spanSize(position)
    }

    protected open fun spanSize(position: Int) = 1

    fun getItem(position: Int): T? {
        val pos = indices[position].first
        return if (pos.isValidPosition(data)) data[pos]
        else null
    }

    fun showLoading(fallback: () -> Unit) {
        if (itemCount == 0) fallback()
        else showLoading()
    }

    fun showLoading() {
        if (!types.contains(R.layout.item_loading)) {
            types.add(R.layout.item_loading)
            indices.add(Pair(R.layout.item_loading, 0))
            notifyItemInserted(types.size)
        }
    }

    fun showError(fallback: () -> Unit) {
        if (itemCount == 0 || !types.contains(R.layout.item_loading)) fallback()
        else showError()
    }

    fun showError() {
        val loadingIndex = types.indexOf(R.layout.item_loading)
        if (loadingIndex >= 0) {
            types.removeAt(loadingIndex)
            notifyItemRemoved(types.size)
        }
        if (!types.contains(R.layout.item_retry)) {
            types.add(R.layout.item_retry)
            indices.add(Pair(R.layout.item_retry, 0))
            notifyItemInserted(types.size)
        }
    }

    fun hideLoading(fallback: () -> Unit) {
        if (itemCount == 0) fallback()
        else hideLoading()
    }

    fun hideLoading() {
        val loadingIndex = types.indexOf(R.layout.item_loading)
        if (loadingIndex >= 0) {
            types.removeAt(loadingIndex)
            notifyItemRemoved(types.size)
        }
    }

    fun appendList(data: List<T>) {
        val loadingIndex = types.indexOf(R.layout.item_loading)
        if (loadingIndex >= 0) {
            types.removeAt(loadingIndex)
            notifyItemRemoved(types.size)
        }
        val lastSize = types.size
        this.data.addAll(data)
        anl()
        notifyItemRangeInserted(lastSize, types.size - lastSize)
    }

    fun showList(data: List<T>) {
        clear()
        this.data.addAll(data)
        anl()
        notifyItemRangeChanged(0, itemCount)
    }

    protected open fun anl() = repeat(data.size) {
        types.add(ViewType.UNIDENTIFIED)
        indices.add(Pair(it, it))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        types.clear()
        indices.clear()
        data.clear()
        notifyDataSetChanged()
    }
}
