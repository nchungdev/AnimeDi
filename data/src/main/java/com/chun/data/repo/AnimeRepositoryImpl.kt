package com.chun.data.repo

import com.chun.data.remote.service.AnimeService
import com.chun.domain.model.Anime
import com.chun.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import java.time.DayOfWeek
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService
) : AnimeRepository {

    override suspend fun getInfo(id: Int) = flow { emit(animeService.getInfo(id)) }

    override suspend fun fetchTop(subtype: String, page: Int) =
        flow { emit(animeService.fetchTop(subtype, page)) }.mapNotNull { it.data }

    override suspend fun fetchSeason(year: Int, season: String): Flow<List<Anime>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSeasonLater(): Flow<List<Anime>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchScheduler(dayOfWeek: DayOfWeek): Flow<List<Anime>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchRecommendations(id: Int): Flow<List<Anime>> {
        TODO("Not yet implemented")
    }
}