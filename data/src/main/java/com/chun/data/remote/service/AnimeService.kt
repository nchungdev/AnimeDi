package com.chun.data.remote.service

import com.chun.data.model.ListResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.Anime
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AnimeService {
    @GET("anime/{id}")
    suspend fun getInfo(@Path("id") type: Int, @QueryMap map: Map<String, String> = emptyMap()): Anime

    @GET("top/anime/{page}/{subtype}")
    suspend fun fetchTop(
        subtype: String,
        page: Int,
        @QueryMap query: Map<String, String> = emptyMap()
    ): ListResult<Anime>

    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

    @GET("season/archive")
    suspend fun fetchSeasonArchive(): SeasonResult

    @GET("season/later")
    suspend fun fetchSeasonLater(): SeasonResult

    @GET("schedule/{day}")
    suspend fun fetchSchedule(@Path("day") day: String): ListResult<Anime>
}