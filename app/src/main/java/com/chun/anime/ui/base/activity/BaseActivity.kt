package com.chun.anime.ui.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chun.anime.ui.base.ViewBindingProvider

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), ViewBindingProvider<VB> {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = provideViewBinding(layoutInflater, null)
        setContentView(binding.root)
        setupViews(savedInstanceState)
        handleEvent(savedInstanceState)
    }

    abstract fun setupViews(savedInstanceState: Bundle?)

    abstract fun handleEvent(savedInstanceState: Bundle?)

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}