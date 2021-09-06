package com.chun.domain.repository

import androidx.paging.PagingData
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

interface AnimeRepository {
    suspend fun getInfo(id: Int): Flow<Resource<Anime>>

    suspend fun fetchTop(subtype: String = ""): Flow<Resource<List<Anime>>>

    suspend fun fetchTop(subtype: String = "", page: Int = 1): Flow<PagingData<Anime>>

    suspend fun fetchSeason(year: Int, season: String): Flow<Resource<List<Anime>>>

    suspend fun fetchSeasonLater(): Flow<Resource<List<Anime>>>

    suspend fun fetchScheduler(dayOfWeek: DayOfWeek): Flow<Resource<List<Anime>>>

    suspend fun fetchRecommendations(id: Int): Flow<Resource<List<Anime>>>
}