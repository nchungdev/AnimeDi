package com.chun.domain.repository

import com.chun.domain.Resource
import com.chun.domain.model.Home
import com.chun.domain.model.Layout
import com.chun.domain.model.Season
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getHome(layouts: List<Layout>): Flow<Resource<List<Home>>>

    suspend fun fetchGenre()

    suspend fun fetchSeason(year: Int, season: String): Season

    fun getTopCharacter()
}