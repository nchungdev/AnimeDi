package com.chun.anime.util

import android.content.Context

object Connection {
    private val manager by lazy { ConnectionStateManager() }

    fun initialize(context: Context) = manager.initialize(context)

    fun registerCallback(callback: ConnectionStateManager.Callback) {
        manager.registerCallback(callback)
    }

    fun unregisterCallback(callback: ConnectionStateManager.Callback) {
        manager.unregisterCallback(callback)
    }
}