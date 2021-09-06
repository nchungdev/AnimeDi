package com.chun.anime.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat

object SystemUtil {
    fun getStatusBarHeight(): Int {
        var result = 0
        val resources = Resources.getSystem()
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getTopInsets(activity: Activity): Int {
        val windowInsetsCompat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.rootWindowInsets
        } else {
            return getStatusBarHeight()
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowInsetsCompat?.getInsets(WindowInsetsCompat.Type.statusBars())?.top ?: 0
        } else {
            windowInsetsCompat.systemWindowInsetTop
        }
    }

    fun toggleKeyword(view: View, show: Boolean) {
        val context = view.context
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            view.requestFocus()
            imm.showSoftInput(view, 0)
        } else {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}