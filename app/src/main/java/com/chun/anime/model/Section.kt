package com.chun.anime.model

import com.chun.domain.model.BaseObj

data class Section(val display: Display, val title: String, val items: List<BaseObj>)