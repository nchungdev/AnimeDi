package com.chun.anime.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chun.anime.ui.base.ViewBindingProvider
import com.chun.anime.ui.base.activity.BaseActivity
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
        provideToolbar()?.let {
            val activity = requireActivity() as BaseActivity<*>
            activity.setSupportActionBar(it)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setupViews(view, savedInstanceState)
        bindData(view, savedInstanceState)
    }

    protected open fun provideToolbar(): Toolbar? = null

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setupViews(view: View, savedInstanceState: Bundle?)

    abstract fun bindData(view: View, savedInstanceState: Bundle?)

    fun setupToolbar(toolbar: Toolbar) {
        val activity = requireActivity() as BaseActivity<*>
        activity.setSupportActionBar(toolbar)
    }
}