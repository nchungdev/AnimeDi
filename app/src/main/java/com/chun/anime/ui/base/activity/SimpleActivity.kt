package com.chun.anime.ui.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chun.anime.R
import com.chun.anime.databinding.ActivitySimpleFragmentBinding

abstract class SimpleActivity : BaseActivity<ActivitySimpleFragmentBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ActivitySimpleFragmentBinding.inflate(inflater, container, false)

    abstract fun provideFragment(): Fragment

    override fun setupViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment, provideFragment())
                .commitNowAllowingStateLoss()
        }
    }

    override fun handleEvent(savedInstanceState: Bundle?) {

    }
}