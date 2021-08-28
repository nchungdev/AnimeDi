package com.chun.domain.repository

import com.chun.domain.model.CacheVersion
import com.chun.domain.model.Home
import kotlinx.coroutines.flow.Flow

interface CacheProvider {
    fun saveHomes(homes: List<Home>)

    fun getHomes(): Flow<CacheVersion<List<Home>>?>
}