package com.chun.data.remote

import com.chun.data.model.RestResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.BaseObj
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("top/{type}/{page}/{subtype}")
    suspend fun fetchTop(
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("subtype") subtype: String
    ): RestResult<BaseObj>


    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

    @GET("top/character/{page}/{subtype}")
    fun getTopCharacter(@Path("page") page: Int, @Path("subtype") subtype: String)
}