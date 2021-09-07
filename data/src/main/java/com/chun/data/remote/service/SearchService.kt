package com.chun.data.remote.service

import com.chun.data.model.ListResult
import com.chun.domain.model.Otaku
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SearchService {

    @GET("search/{type}")
    suspend fun search(@Path("type") type: String, @QueryMap params: Map<String, String>): ListResult<Otaku>
}