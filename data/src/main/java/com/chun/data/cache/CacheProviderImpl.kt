package com.chun.data.cache

import com.chun.domain.model.CacheVersion
import com.chun.domain.model.Home
import com.chun.domain.repository.CacheProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheProviderImpl @Inject constructor(private val fileCacheHelper: FileCacheHelper) : CacheProvider {

    override fun saveHomes(homes: List<Home>) {
        if (homes.isEmpty()) {
            fileCacheHelper.putCache(HOME, null)
        } else {
            val cacheVersion = CacheVersion(homes, System.currentTimeMillis() + CACHE_EXPIRED_TIME)
            val type = object : TypeToken<CacheVersion<List<Home>>>() {}.type
            fileCacheHelper.putCache(HOME, Gson().toJson(cacheVersion, type))
        }
    }

    override fun getHomes() = flowOf(
        try {
            val cache = fileCacheHelper.getCache(HOME)
            val type = object : TypeToken<CacheVersion<List<Home>>>() {}.type
            Gson().fromJson(cache, type) as CacheVersion<List<Home>>
        } catch (e: Exception) {
            null
        }
    )

    companion object {
        const val HOME = "home_cache"

        val CACHE_EXPIRED_TIME = TimeUnit.DAYS.toMillis(1)
    }
}