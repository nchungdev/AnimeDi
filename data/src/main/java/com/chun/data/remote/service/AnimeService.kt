package com.chun.data.remote.service

import com.chun.data.model.RestListResult
import com.chun.data.model.SeasonResult
import com.chun.domain.model.Anime
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.time.MonthDay

interface AnimeService {
    @GET("anime/{id}")
    suspend fun getInfo(@Path("id") type: Int): Anime

    @GET("top/anime")
    suspend fun fetchTop(@QueryMap query: HashMap<String, String>): RestListResult<Anime>

    @GET("season/{year}/{season}")
    suspend fun fetchSeason(@Path("year") year: Int, @Path("season") season: String): SeasonResult

    @GET("season/archive")
    suspend fun fetchSeasonArchive(): SeasonResult

    @GET("season/later")
    suspend fun fetchSeasonLater(): SeasonResult

    @GET("schedule/{day}")
    suspend fun fetchSchedule(@Path("day") day: String): RestListResult<Anime>
}