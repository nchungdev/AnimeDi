package com.chun.domain.repository

import com.chun.domain.Resource
import com.chun.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getInfo(id: Int): Flow<Resource<Anime>>
}