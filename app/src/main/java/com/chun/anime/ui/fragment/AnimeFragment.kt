package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.chun.anime.databinding.FragmentAnimeBinding
import com.chun.anime.ui.activity.AnimeActivity
import com.chun.anime.ui.base.activity.BaseActivity
import com.chun.anime.ui.base.fragment.BaseFragment
import com.chun.anime.util.glide.loadBlurBg
import com.chun.anime.util.glide.loadThumbnail
import com.chun.anime.util.hide
import com.chun.anime.util.show
import com.chun.anime.viewmodel.AnimeViewModel
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeFragment : BaseFragment<FragmentAnimeBinding>() {

    private val viewModel: AnimeViewModel by viewModels()

    private lateinit var requestManager: RequestManager

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAnimeBinding.inflate(inflater, container, false)

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as BaseActivity<*>).setSupportActionBar(binding.toolbar)
        requestManager = Glide.with(this)
        binding.imgBg.setOnApplyWindowInsetsListener { v, insets ->
            insets.replaceSystemWindowInsets(0, 0, 0, 0)
            return@setOnApplyWindowInsetsListener insets
        }
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        val otaku: Otaku = arguments?.getParcelable(AnimeActivity.EXTRA_DATA) ?: return
        viewModel.getInfo(otaku)
        requestManager
            .loadThumbnail(otaku.imageUrl, isLightTheme)
            .into(binding.imgCover)
        requestManager
            .loadBlurBg(otaku.imageUrl, isLightTheme)
            .into(binding.imgBg)
        binding.tvTitle.text = otaku.name

        viewModel.animeInfo.observe(viewLifecycleOwner) {
            val resource = it ?: return@observe
            when (resource) {
                Resource.Loading -> showLoading()
                is Resource.Error -> showError(resource.exception)
                is Resource.Success -> {
                    hideLoading()
                    hideError()
                    val anime = resource.data
                    binding.tvDesc.text = anime.synopsis
                }
            }
        }
    }

    private fun hideError() {

    }

    private fun showError(exception: Throwable) {

    }

    private fun showLoading() {
        binding.content.loading.progressBar.show()
    }

    private fun hideLoading() {
        binding.content.loading.progressBar.hide()
    }

    companion object {
        fun newInstance(otaku: Otaku) = AnimeFragment().apply {
            arguments = bundleOf(AnimeActivity.EXTRA_DATA to otaku)
        }
    }
}