package com.chun.data.repo

import androidx.paging.PagingData
import com.chun.data.remote.service.AnimeService
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import com.chun.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.DayOfWeek
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService
) : AnimeRepository {
    override suspend fun getInfo(id: Int) = flowOf(
        try {
            Resource.Success(animeService.getInfo(id))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    )

    override suspend fun fetchTop(subtype: String): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTop(subtype: String, page: Int): Flow<PagingData<Anime>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSeason(year: Int, season: String): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSeasonLater(): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchScheduler(dayOfWeek: DayOfWeek): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchRecommendations(id: Int): Flow<Resource<List<Anime>>> {
        TODO("Not yet implemented")
    }
}