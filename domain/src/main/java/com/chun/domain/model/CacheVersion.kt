package com.chun.domain.model

class CacheVersion<T>(
    val data: T? = null,
    private val expiredTime: Long = 0L
) {

    fun isExpired() = System.currentTimeMillis() - expiredTime >= 0
}