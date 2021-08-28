package com.chun.anime.util.glide

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.chun.anime.AnimeApp
import com.chun.anime.R
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

private val roundedCornersTransformation by lazy {
    RoundedCornersTransformation(
        AnimeApp.instance.resources.getDimensionPixelSize(R.dimen.thumb_round_radius),
        0
    )
}

fun RequestManager.loadThumbnail(url: String, lightTheme: Boolean): RequestBuilder<Drawable> {
    val requestOption = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .signature(ObjectKey("thumb.$url.$lightTheme"))
        .transform(MultiTransformation(CenterCrop(), roundedCornersTransformation))
    return load(url).apply(requestOption)
}

fun RequestManager.loadThumbnail(url: String): RequestBuilder<Drawable> {
    val requestOption = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .signature(ObjectKey("thumb.$url"))
        .transform(MultiTransformation(CenterCrop(), roundedCornersTransformation))
    return load(url).apply(requestOption)
}

fun RequestManager.loadBlurBg(
    url: String,
    lightTheme: Boolean = true,
    radius: Int = 25,
    sampling: Int = 1
): RequestBuilder<Drawable> {
    val requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .signature(ObjectKey("bg_blur.$url.$lightTheme"))
        .transform(MultiTransformation(CenterCrop(), BlurTransformation(radius, sampling)))
    return load(url).apply(requestOptions)
}

fun RequestManager.loadBg(url: String): RequestBuilder<Drawable> {
    val requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .signature(ObjectKey("bg.$url"))
        .transform(MultiTransformation(CenterCrop(), BlurOverlayTransformation(R.drawable.overlay_home_background)))
    return load(url).apply(requestOptions)
}

class SimpleRequestListener<R>(
    private val onSuccess: (resource: R) -> Unit = { },
    private val onFailure: (e: GlideException?) -> Unit = { }
) : RequestListener<R> {

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<R>?, isFirstResource: Boolean): Boolean {
        onFailure(e)
        return false
    }

    override fun onResourceReady(
        resource: R,
        model: Any?,
        target: Target<R>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        onSuccess(resource)
        return false
    }
}