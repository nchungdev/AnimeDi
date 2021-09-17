package com.chun.anime.util.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.glide.transformations.BlurTransformation
import java.security.MessageDigest
import java.util.*

class BlurOverlayTransformation(private val overlayResId: Int) : BlurTransformation(25, 1) {
    private var overlay: Drawable? = null

    override fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val transform = super.transform(context, pool, toTransform, outWidth, outHeight)
        if (overlayResId == 0) {
            return transform
        }
        if (overlay == null) {
            overlay = ContextCompat.getDrawable(context, overlayResId)
        }
        val drawables = arrayOfNulls<Drawable>(2)
        drawables[0] = BitmapDrawable(context.resources, transform)
        drawables[1] = overlay
        val drawable = LayerDrawable(drawables)
        val bitmap = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as BlurOverlayTransformation
        return overlayResId == that.overlayResId
    }

    override fun hashCode(): Int {
        return ID.hashCode() + Objects.hash(overlayResId)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        super.updateDiskCacheKey(messageDigest)
        messageDigest.update((ID + overlayResId).toByteArray(CHARSET))
    }

    companion object {
        private const val VERSION = 1
        private const val ID = "com.chun.glide.transformations.BlurOverlayTransformation.$VERSION"
    }
}