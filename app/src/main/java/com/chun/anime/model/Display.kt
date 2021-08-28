package com.chun.anime.model

sealed class Display(val type: Int) {
    object Carousel : Display(0)
    object Landscape : Display(1)
    object Portrait : Display(2)
    object Header : Display(3)
}