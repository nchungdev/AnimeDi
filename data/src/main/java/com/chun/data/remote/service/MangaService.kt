package com.chun.data.remote.service

import com.chun.domain.model.Manga
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MangaService {
    @GET("manga/{id}")
    suspend fun getInfo(@Path("id") type: Int, @QueryMap map: Map<String, String>): Manga

    @GET("top/character/{page}/{subtype}")
    suspend fun getTopCharacter(@Path("page") page: Int, @Path("subtype") subtype: String)
}