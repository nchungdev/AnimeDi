package com.chun.data.repo

import com.chun.data.remote.RestApi
import com.chun.domain.model.BaseObj
import com.chun.domain.model.Season
import com.chun.domain.repository.RestRepository
import javax.inject.Inject

class RestRepositoryImpl @Inject constructor(private val restApi: RestApi) : RestRepository {

    override suspend fun fetchTop(type: String, subtype: String, page: Int): List<BaseObj> {
        return restApi.fetchTop(type, page, subtype).data
    }

    override suspend fun fetchSeason(year: Int, season: String): Season {
        val seasonResult = restApi.fetchSeason(year, season)
        return Season(
            seasonResult.sessionName,
            seasonResult.sessionYear,
            seasonResult.data,
        )
    }

    override suspend fun fetchGenre() {

    }

    override fun getTopCharacter() {

    }
}