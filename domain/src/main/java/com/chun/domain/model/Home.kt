package com.chun.domain.model

import com.chun.domain.model.type.Display

data class Home(
    val title: MultiLang = MultiLang(),
    val subtitle: MultiLang = MultiLang(),
    val items: List<Otaku> = arrayListOf(),
    val display: Int = Display.UNIDENTIFIED
) {
    companion object {
        val EMPTY = Home()
    }
}