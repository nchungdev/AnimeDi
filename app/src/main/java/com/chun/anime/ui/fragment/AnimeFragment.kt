package com.chun.anime.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chun.anime.databinding.FragmentAnimeBinding

class AnimeFragment : BaseFragment<FragmentAnimeBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAnimeBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance(subtype: String): AnimeFragment {
            return AnimeFragment()
        }
    }
}