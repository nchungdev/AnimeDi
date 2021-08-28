package com.chun.data.repo

import com.chun.data.model.RestListResult
import com.chun.data.remote.service.HomeService
import com.chun.data.util.NetworkBoundResource
import com.chun.domain.model.Home
import com.chun.domain.model.Layout
import com.chun.domain.model.Otaku
import com.chun.domain.model.Season
import com.chun.domain.repository.CacheProvider
import com.chun.domain.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val cacheProvider: CacheProvider
) : HomeRepository {

    override suspend fun getHome(layouts: List<Layout>) = NetworkBoundResource(
        localFetch = { cacheProvider.getHomes() },
        remoteFetch = {
            layouts.map { layout ->
                val dataType = layout.dataType
                when (dataType.reference) {
                    "top" -> process(layout, homeService.fetchTop(dataType.type, 1, dataType.subtype))
                    else -> Home.EMPTY
                }
            }
        },
        storeRemoteResult = { cacheProvider.saveHomes(it) }
    )

    private fun process(layout: Layout, otakuList: RestListResult<Otaku>): Home {
        return if (otakuList.data == null) Home.EMPTY
        else {
            otakuList.data?.forEach { it.type = layout.dataType.type }
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