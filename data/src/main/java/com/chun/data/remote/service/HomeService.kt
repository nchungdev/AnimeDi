package com.chun.data.remote.service

import com.chun.data.model.ListResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.Otaku
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface HomeService {
    @GET("{path}")
    suspend fun fetchTop(@Path("path") path: String, @QueryMap subtype: Map<String, String>): ListResult<Otaku>

    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

}