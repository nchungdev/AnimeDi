package com.chun.anime

import android.app.Application
import com.chun.anime.util.Connection
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class AnimeApp : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Connection.initialize(this)
        Firebase.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        lateinit var instance: AnimeApp
    }
}