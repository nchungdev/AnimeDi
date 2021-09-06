package com.chun.anime.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface ViewBindingProvider<VB : ViewBinding> {
    fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}