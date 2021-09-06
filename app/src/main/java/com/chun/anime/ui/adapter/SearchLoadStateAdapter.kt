package com.chun.anime.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.chun.anime.databinding.ItemLoadingMoreBinding
import com.chun.anime.ui.base.rv.BaseLoadStateAdapter
import com.chun.anime.ui.base.rv.ViewHolder

class SearchLoadStateAdapter(context: Context, private val retry: () -> Unit) :
    BaseLoadStateAdapter<ItemLoadingMoreBinding>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        ViewHolder(ItemLoadingMoreBinding.inflate(inflater, parent, false)).apply {
            binding.retryButton.setOnClickListener { retry() }
        }

    override fun onBindViewHolder(holder: ViewHolder<ItemLoadingMoreBinding>, loadState: LoadState) {
        if (loadState is LoadState.Error) {
            holder.binding.errorMsg.text = loadState.error.localizedMessage
        }
        holder.binding.progressBar.isVisible = loadState is LoadState.Loading
        holder.binding.retryButton.isVisible = loadState is LoadState.Error
        holder.binding.errorMsg.isVisible = loadState is LoadState.Error
    }
}
