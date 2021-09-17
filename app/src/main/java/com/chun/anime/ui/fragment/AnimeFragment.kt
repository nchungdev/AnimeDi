package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.signature.ObjectKey
import com.chun.anime.R
import com.chun.anime.databinding.FragmentAnimeBinding
import com.chun.anime.ui.activity.AnimeActivity
import com.chun.anime.ui.adapter.AnimeFragmentStateAdapter
import com.chun.anime.ui.base.activity.BaseActivity
import com.chun.anime.ui.base.fragment.BaseFragment
import com.chun.anime.util.glide.loadThumbnail
import com.chun.anime.util.openYoutubeLink
import com.chun.anime.viewmodel.AnimeViewModel
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import com.google.android.material.tabs.TabLayoutMediator
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
        binding.top.setOnApplyWindowInsetsListener { v, insets ->
            insets.replaceSystemWindowInsets(0, 0, 0, 0)
            return@setOnApplyWindowInsetsListener insets
        }

    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        val otaku: Otaku = arguments?.getParcelable(AnimeActivity.EXTRA_DATA) ?: return
        viewModel.getInfo(otaku)
        requestManager
            .asBitmap()
            .load(otaku.imageUrl)
            .signature(ObjectKey("bg.${otaku.imageUrl}"))
            .centerCrop()
            .into(binding.imgBg)
        requestManager
            .loadThumbnail(otaku.imageUrl)
            .into(binding.thumbnail)

        binding.toolbar.title = ""
        binding.tvTitle.text = otaku.name
        binding.tvScore.text = getString(R.string.score_format, otaku.score)
        val fragmentStateAdapter = AnimeFragmentStateAdapter(this)
        binding.viewPager.adapter = fragmentStateAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentStateAdapter.getPageTitle(position)
        }.attach()
        viewModel.animeInfo.observe(viewLifecycleOwner) {
            val resource = it ?: return@observe
            when (resource) {
                Resource.Loading -> showLoading()
                is Resource.Error -> showError(resource.exception)
                is Resource.Success -> {
                    hideLoading()
                    hideError()
                    val anime = resource.data
                    binding.tvGenres.text = anime.genres.toString()
                    binding.tvSubtitle.text = anime.duration
                    binding.tvRated.text = anime.rating
                    binding.btnPlay.setOnClickListener {
                        requireActivity().openYoutubeLink(anime.trailerUrl)
                    }
                }
            }
        }
    }

    override fun provideToolbar() = binding.toolbar

    private fun hideError() {

    }

    private fun showError(exception: Throwable) {

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

    companion object {
        fun newInstance(otaku: Otaku) = AnimeFragment().apply {
            arguments = bundleOf(AnimeActivity.EXTRA_DATA to otaku)
        }
    }
}