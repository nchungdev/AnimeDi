package com.chun.anime.util.glide

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import androidx.core.graphics.toRectF

class RoundedDrawable(color: Int, private val radius: Float) : ColorDrawable(color) {
    private val paint = Paint().apply {
        setColor(color)
    }

    override fun draw(canvas: Canvas) {
        val corners = floatArrayOf(
            radius, radius,   // Top left radius in px
            radius, radius,   // Top right radius in px
            radius, radius,     // Bottom right radius in px
            radius, radius      // Bottom left radius in px
        )
        val path = Path()
        path.addRoundRect(bounds.toRectF(), corners, Path.Direction.CW)
        canvas.drawPath(path, paint)
    }
}