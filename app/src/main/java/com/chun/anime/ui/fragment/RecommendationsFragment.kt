package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.chun.anime.databinding.RecyclerviewLayoutBinding
import com.chun.anime.ui.base.fragment.BaseFragment

class RecommendationsFragment : BaseFragment<RecyclerviewLayoutBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): RecyclerviewLayoutBinding {
        return RecyclerviewLayoutBinding.inflate(inflater, container, false)
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {

    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(animeId: Int) = RecommendationsFragment().apply {
            arguments = bundleOf("animeId" to animeId)
        }
    }
}