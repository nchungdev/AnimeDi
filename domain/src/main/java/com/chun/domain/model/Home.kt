package com.chun.domain.model

data class Home(
    val title: MultiLang = MultiLang(),
    val subtitle: MultiLang = MultiLang(),
    val items: List<Otaku> = arrayListOf(),
    val display: Display = Display.UNKNOWN_DISPLAY
) {
    companion object {
        val EMPTY = Home()
    }
}