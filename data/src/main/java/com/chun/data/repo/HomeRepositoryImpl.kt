package com.chun.data.repo

import com.chun.data.model.ListResult
import com.chun.data.remote.service.HomeService
import com.chun.data.util.NetworkBoundResource
import com.chun.domain.model.*
import com.chun.domain.repository.CacheProvider
import com.chun.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val cacheProvider: CacheProvider
) : HomeRepository {

    override suspend fun getHome(layouts: List<Layout>) = NetworkBoundResource(
        localFetch = { cacheProvider.getHomes() },
        remoteFetch = {
            layouts.map { layout ->
                layout.request
                    .takeIf { it != Request.DEFAULT_REQUEST }
                    ?.let { process(layout, homeService.fetchTop(it.path, it.query)) }
                    ?: Home.EMPTY
            }
        },
        storeRemoteResult = { cacheProvider.saveHomes(it) }
    )

    private fun process(layout: Layout, otakuList: ListResult<Otaku>): Home {
        return if (otakuList.data == null) Home.EMPTY
        else {
            otakuList.data?.forEach { it.type = layout.type }
            Home(layout.title, layout.subtitle, otakuList.data!!, layout.display)
        }
    }

    override suspend fun fetchSeason(year: Int, season: String): Season {
        val seasonResult = homeService.fetchSeason(year, season)
        return Season(
            seasonResult.sessionName,
            seasonResult.sessionYear,
            seasonResult.data!!,
        )
    }

    override suspend fun fetchGenre() {

    }

    override fun getTopCharacter() {

    }
}