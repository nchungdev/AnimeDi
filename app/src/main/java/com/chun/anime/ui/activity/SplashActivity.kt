package com.chun.anime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chun.anime.databinding.ActivitySplashBinding
import com.chun.anime.ui.base.activity.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ActivitySplashBinding.inflate(inflater)

    override fun setupViews(savedInstanceState: Bundle?) = Unit

    override fun handleEvent(savedInstanceState: Bundle?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
