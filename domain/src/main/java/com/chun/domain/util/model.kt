package com.chun.domain.util

import com.chun.domain.model.Simple

fun List<Simple>.join(): String {
    return joinToString { ", " }
}