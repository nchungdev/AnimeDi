package com.chun.anime.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chun.anime.ui.base.ViewBindingProvider
import com.chun.anime.util.UiUtil

abstract class BaseFragment<VB : ViewBinding> : Fragment(), ViewBindingProvider<VB> {
    private var _binding: VB? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    protected val isLightTheme by lazy { UiUtil.isLightTheme(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null)
            _binding = provideViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view, savedInstanceState)
        bindData(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setupViews(view: View, savedInstanceState: Bundle?)

    abstract fun bindData(view: View, savedInstanceState: Bundle?)
}