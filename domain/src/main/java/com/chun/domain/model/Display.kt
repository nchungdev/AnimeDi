package com.chun.domain.model

import com.chun.domain.model.type.ViewType

data class Display(@ViewType val viewType: Int = ViewType.UNIDENTIFIED, val seeMore: Boolean = false) {
    companion object {
        val UNKNOWN_DISPLAY = Display()
    }
}
