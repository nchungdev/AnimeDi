package com.chun.domain.util

import timber.log.Timber

fun <T> monitor(tag: String, block: () -> T): T {
    Timber.e("monitor $tag start")
    val time = System.currentTimeMillis()
    val block1 = block()
    Timber.e("monitor $tag end ${System.currentTimeMillis() - time} : $block1")
    return block1
}

suspend fun <T> monitorSuspend(tag: String, block: suspend () -> T): T {
    Timber.e("monitor $tag start")
    val time = System.currentTimeMillis()
    val block1 = block()
    Timber.e("monitor $tag end ${System.currentTimeMillis() - time}")
    return block1
}