package com.chun.anime.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chun.anime.databinding.FragmentHomeBinding
import com.chun.anime.ui.adapter.HomeAdapter
import com.chun.anime.viewmodel.HomeViewModel
import com.chun.domain.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: HomeAdapter

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = binding.recyclerViewLayout.progressBar
        val recyclerView = binding.recyclerViewLayout.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.left = 32
                    outRect.top = 32
                }
            })
        }
        homeViewModel.homeItemList.observe(viewLifecycleOwner) { resource ->
            resource ?: return@observe
            when (resource) {
                is Resource.Error -> progressBar.isVisible = false
                Resource.Loading -> progressBar.isVisible = true
                is Resource.Success -> {
                    progressBar.isVisible = false
                    val context = context ?: return@observe
                    recyclerView.adapter = HomeAdapter(context, ArrayList(resource.data))
                }
            }
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
