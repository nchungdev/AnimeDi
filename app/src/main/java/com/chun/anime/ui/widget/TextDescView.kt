package com.chun.anime.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat.setTextAppearance
import com.chun.anime.R

class TextDescView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tvTitle: TextView
    private val tvDesc: TextView

    init {
        orientation = VERTICAL
        tvTitle = SmartTextView(context)
        tvTitle.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addView(tvTitle)
        tvDesc = SmartTextView(context)
        tvDesc.setPaddingRelative(0, 4, 0, 0)
        tvDesc.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addView(tvDesc)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextDescView)
        try {
//            setTextAppearance(
//                tvTitle,
//                typedArray.getResourceId(R.styleable.TextDescView_titleTextAppearance, 0)
//            )
//            setTextAppearance(
//                tvDesc,
//                typedArray.getResourceId(R.styleable.TextDescView_descTextAppearance, 0)
//            )
            setTitle(typedArray.getString(R.styleable.TextDescView_title) ?: "")
            setDesc(typedArray.getString(R.styleable.TextDescView_desc) ?: "")
        } finally {
            typedArray.recycle()
        }

    }

    fun setTitle(title: CharSequence) {
        tvTitle.text = title
    }

    fun setDesc(desc: CharSequence) {
        tvDesc.text = desc
    }
}