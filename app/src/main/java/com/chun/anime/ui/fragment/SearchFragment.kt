package com.chun.anime.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chun.anime.databinding.FragmentSearchBinding
import com.chun.anime.viewmodel.SearchViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)


    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}
