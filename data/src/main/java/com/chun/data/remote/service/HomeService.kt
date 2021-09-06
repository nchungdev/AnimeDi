package com.chun.data.remote.service

import com.chun.data.model.RestListResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.Otaku
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("top/{type}/{page}/{subtype}")
    suspend fun fetchTop(
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("subtype") subtype: String
    ): RestListResult<Otaku>


    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

    @GET("top/character/{page}/{subtype}")
    fun getTopCharacter(@Path("page") page: Int, @Path("subtype") subtype: String)
}