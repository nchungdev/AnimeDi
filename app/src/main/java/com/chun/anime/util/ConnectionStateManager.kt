package com.chun.anime.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest


class ConnectionStateManager {

    private val callbacks = ArrayList<Callback>()

    fun initialize(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                callbacks.forEach { it.onChange(true) }
            }

            override fun onLost(network: Network) {
                callbacks.forEach { it.onChange(false) }
            }
        })
    }

    fun registerCallback(callback: Callback) {
        callbacks.add(callback)
    }

    fun unregisterCallback(callback: Callback) {
        callbacks.remove(callback)
    }

    interface Callback {
        fun onChange(connected: Boolean)
    }

    companion object {
        val instance by lazy { ConnectionStateManager() }
    }
}