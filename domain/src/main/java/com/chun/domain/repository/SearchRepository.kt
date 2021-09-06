package com.chun.domain.repository

import androidx.paging.PagingData
import com.chun.domain.model.Otaku
import com.chun.domain.param.SearchParams
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchParams: SearchParams): Flow<PagingData<Otaku>>
}