package com.chun.domain.repository

import com.chun.domain.model.CacheVersion
import com.chun.domain.model.Home
import kotlinx.coroutines.flow.Flow

interface CacheProvider {
    fun save(type: String, homes: List<Home>)

    fun get(type: String): Flow<CacheVersion<List<Home>>?>
}