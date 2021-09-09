package com.chun.domain.repository

import com.chun.domain.model.Anime
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

interface AnimeRepository {
    suspend fun getInfo(id: Int): Flow<Anime>

    suspend fun fetchTop(subtype: String = "", page: Int): Flow<List<Anime>>

    suspend fun fetchSeason(year: Int, season: String): Flow<List<Anime>>

    suspend fun fetchSeasonLater(): Flow<List<Anime>>

    suspend fun fetchScheduler(dayOfWeek: DayOfWeek): Flow<List<Anime>>

    suspend fun fetchRecommendations(id: Int): Flow<List<Anime>>
}