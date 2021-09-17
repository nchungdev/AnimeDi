package com.chun.anime.ui.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.widget.AppCompatEditText


class SearchEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    var onQueryTextListener: OnQueryTextListener? = null

    init {
        imeOptions = EditorInfo.IME_ACTION_SEARCH
        setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onQueryTextListener?.onQueryTextSubmit(text?.toString() ?: return@OnEditorActionListener true)
                return@OnEditorActionListener true
            }
            false
        })
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onQueryTextListener?.onQueryTextChange(text?.toString() ?: return)
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    interface OnQueryTextListener {
        fun onQueryTextSubmit(query: String)
        fun onQueryTextChange(newText: String)
    }
}