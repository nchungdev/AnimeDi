package com.chun.domain.model

import java.util.*

data class Timestamp(
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    var month: Int = Calendar.getInstance().get(Calendar.MONTH),
    var year: Int = Calendar.getInstance().get(Calendar.YEAR)
) {
    companion object {
        val CURRENT = Timestamp()
    }
}