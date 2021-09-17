package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.chun.anime.databinding.RecyclerviewLayoutBinding
import com.chun.anime.ui.base.fragment.BaseFragment

class AboutAnimeFragment : BaseFragment<RecyclerviewLayoutBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        RecyclerviewLayoutBinding.inflate(inflater, container, false)

    override fun setupViews(view: View, savedInstanceState: Bundle?) {

    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(animeId: Int) = AboutAnimeFragment().apply {
            arguments = bundleOf("animeId" to animeId)
        }
    }
}