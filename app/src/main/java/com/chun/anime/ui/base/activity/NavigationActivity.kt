package com.chun.anime.ui.base.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.chun.anime.R
import java.util.*

abstract class NavigationActivity<VB : ViewBinding> : BaseActivity<VB>() {

    private val fragments = WeakHashMap<String, Fragment>()

    fun navigate(name: String) {
        val targetFragment = supportFragmentManager.findFragmentByTag(name)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = getFragment(name)
        if (targetFragment == null && !fragment.isAdded) {
            transaction.add(getContainerId(), fragment)
            fragments[name] = fragment
        } else {
            transaction.show(fragment)
            transaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
        }
        fragments.filter { it.key != name }.map { it.value }.forEach {
            transaction.setMaxLifecycle(it, Lifecycle.State.STARTED)
            transaction.hide(it)
        }
        transaction.commitNowAllowingStateLoss()
    }

    private fun getFragment(name: String) = fragments[name] ?: createFragment(name)

    abstract fun createFragment(name: String): Fragment

    protected abstract fun provideNavigationTags(): Array<String>

    protected open fun getContainerId() = R.id.fragment

    override fun onDestroy() {
        fragments.clear()
        super.onDestroy()
    }
}
