package com.chun.anime.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null)
            _binding = inflate(inflater, container)
        return binding.root
    }

    protected abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): T?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}