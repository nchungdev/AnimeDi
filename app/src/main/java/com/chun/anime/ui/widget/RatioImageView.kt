package com.chun.anime.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.chun.anime.R
import kotlin.math.min

class RatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var ratio: Float = 1f
        set(value) {
            field = value
            requestLayout()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView)
        try {
            val ratioStr = typedArray.getString(R.styleable.RatioImageView_ratio)
            ratioStr?.split(":")?.let {
                ratio = it[0].toFloat() / it[1].toFloat()
            }
        } finally {
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ratio == 0f) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height =
                if (heightMode == MeasureSpec.AT_MOST) {
                    min((width * ratio).toInt(), MeasureSpec.getSize(heightMeasureSpec))
                } else {
                    (width * ratio).toInt()
                }
            setMeasuredDimension(width, height)
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            val height = MeasureSpec.getSize(heightMeasureSpec)
            val width =
                if (heightMode == MeasureSpec.AT_MOST) {
                    min((height / ratio).toInt(), MeasureSpec.getSize(widthMeasureSpec))
                } else {
                    (height / ratio).toInt()
                }
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}