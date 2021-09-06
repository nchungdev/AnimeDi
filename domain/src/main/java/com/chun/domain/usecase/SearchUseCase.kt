package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.model.Otaku
import com.chun.domain.param.SearchParams
import com.chun.domain.repository.SearchRepository
import com.chun.domain.usecase.base.PagingUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    PagingUseCase<SearchParams, Otaku>(dispatcher) {

    override fun execute(params: SearchParams) = flow {
        emitAll(searchRepository.search(params))
    }
}
