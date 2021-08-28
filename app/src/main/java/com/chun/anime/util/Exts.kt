package com.chun.anime.util

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.chun.domain.model.MultiLang

fun MultiLang.get(context: Context): String {
    val locale = ConfigurationCompat.getLocales(context.resources.configuration)[0]
    return when {
        locale.equals("vi") -> vi
        else -> en
    }
}
