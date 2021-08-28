package com.chun.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.chun.data.paging.datasource.SearchPagingDataSource
import com.chun.data.remote.service.SearchService
import com.chun.domain.param.SearchParams
import com.chun.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) : SearchRepository {
    override suspend fun search(searchParams: SearchParams) = Pager(
        config = PagingConfig(pageSize = 50, prefetchDistance = 2),
        pagingSourceFactory = { SearchPagingDataSource(searchService, searchParams) }
    ).flow
}
