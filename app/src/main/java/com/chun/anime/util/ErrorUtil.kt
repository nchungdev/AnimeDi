package com.chun.anime.util

import android.content.Context

object ErrorUtil {
    fun makeErrorMessage(context: Context, error: Throwable?): String?  {
        return error?.message
    }

}
