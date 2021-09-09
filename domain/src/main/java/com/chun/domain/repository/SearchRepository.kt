package com.chun.domain.repository

import com.chun.domain.model.Otaku
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(type: String, keyword: String, page: Int): Flow<List<Otaku>>
}