package com.chun.domain.model

data class Related(
    var adaptation: List<Simple> = emptyList(),
    var other: List<Simple> = emptyList(),
    var prequel: List<Simple> = emptyList(),
    var sequel: List<Simple> = emptyList(),
    var sidestory: List<Simple> = emptyList(),
    var spinOff: List<Simple> = emptyList()
)