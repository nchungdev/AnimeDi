package com.chun.anime.util.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.glide.transformations.BitmapTransformation
import jp.wasabeef.glide.transformations.BlurTransformation
import java.security.MessageDigest

class BlurPaletteTransformation : BitmapTransformation() {

    override fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val dominantColor = Palette.Builder(toTransform).generate().getDominantColor(Color.BLACK)
        val colorDrawable = ColorDrawable(dominantColor)
        colorDrawable.setBounds(0, 0, 0, 0)
        val drawables = arrayOf(
            BitmapDrawable(context.resources, toTransform),
//            colorDrawable
        )
        val layerDrawable = LayerDrawable(drawables)
        val bitmap = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        layerDrawable.setBounds(0, 0, canvas.width, canvas.height)
        layerDrawable.draw(canvas)
        return bitmap
    }

    override fun equals(o: Any?): Boolean {
        return o is BlurTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID).toByteArray(CHARSET))
    }

    companion object {
        private const val VERSION = 1
        private const val ID = "jp.wasabeef.glide.transformations.BlurPaletteTransformation.$VERSION"
    }
}