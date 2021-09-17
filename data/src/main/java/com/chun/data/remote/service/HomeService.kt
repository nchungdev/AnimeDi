package com.chun.data.remote.service

import com.chun.data.model.ListResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.Otaku
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface HomeService {
    @GET
    suspend fun fetchData(@Url path: String, @QueryMap subtype: Map<String, String>): ListResult<Otaku>

    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

}