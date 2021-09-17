package com.chun.data.cache

import com.chun.domain.model.CacheVersion
import com.chun.domain.model.Home
import com.chun.domain.repository.CacheProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheProviderImpl @Inject constructor(private val fileCacheHelper: FileCacheHelper) : CacheProvider {

    override fun save(type: String, homes: List<Home>) {
        if (homes.isEmpty()) {
            fileCacheHelper.putCache(type, null)
        } else {
            val cacheVersion = CacheVersion(homes, System.currentTimeMillis() + CACHE_EXPIRED_TIME)
            fileCacheHelper.putCache(
                type,
                Gson().toJson(cacheVersion, object : TypeToken<CacheVersion<List<Home>>>() {}.type)
            )
        }
    }

    override fun get(type: String) = flowOf(
        try {
            Gson().fromJson(
                fileCacheHelper.getCache(type),
                object : TypeToken<CacheVersion<List<Home>>>() {}.type
            ) as CacheVersion<List<Home>>
        } catch (e: Exception) {
            null
        }
    )

    companion object {
        const val HOME = "home_cache"

        val CACHE_EXPIRED_TIME = TimeUnit.DAYS.toMillis(1)
    }
}