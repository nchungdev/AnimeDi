package com.chun.anime.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import com.chun.anime.databinding.FragmentExploreBinding
import com.chun.anime.ui.activity.SearchActivity
import com.chun.anime.ui.base.activity.BaseActivity
import com.chun.anime.ui.base.fragment.BaseFragment

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentExploreBinding.inflate(inflater, container, false)

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (activity as BaseActivity<*>).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(context, "Home", Toast.LENGTH_LONG).show()
        }
        binding.searchView.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, null)
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        binding.apply {
//            val fragmentAdapter = ExploreFragmentAdapter(this@ExploreFragment)
//            viewPager.adapter = fragmentAdapter
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                tab.text = fragmentAdapter.getPageTitle(position)
//            }.attach()
        }
    }
}