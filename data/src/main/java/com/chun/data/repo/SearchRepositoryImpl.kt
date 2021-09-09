package com.chun.data.repo

import com.chun.data.remote.service.SearchService
import com.chun.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) : SearchRepository {
    override suspend fun search(type: String, keyword: String, page: Int) =
        flow { emit(searchService.search(type, mapOf("q" to keyword, "page" to page.toString(), "limit" to "48"))) }
            .mapNotNull { it.data }
}
