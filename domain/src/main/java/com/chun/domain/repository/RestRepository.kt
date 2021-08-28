package com.chun.domain.repository

import com.chun.domain.model.BaseObj
import com.chun.domain.model.Season

interface RestRepository {

    suspend fun fetchTop(type: String, subtype: String, page: Int): List<BaseObj>

    suspend fun fetchGenre()

    suspend fun fetchSeason(year: Int, season: String): Season

    fun getTopCharacter()
}