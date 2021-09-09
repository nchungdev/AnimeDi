package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import com.chun.domain.repository.SearchRepository
import com.chun.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    FlowUseCase<SearchUseCase.Params, List<Otaku>>(dispatcher) {

    override fun execute(params: Params) =
        flow { emitAll(searchRepository.search(params.type, params.keyword, params.page)) }.map { Resource.Success(it) }

    data class Params(val type: String, val keyword: String, val page: Int)
}
