package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.model.BaseObj
import com.chun.domain.repository.RestRepository
import com.chun.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchTopUseCase @Inject constructor(
    private val restRepository: RestRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    CoroutineUseCase<FetchTopUseCase.Params, List<BaseObj>>(dispatcher) {

    override suspend fun execute(params: Params): List<BaseObj> {
        return restRepository.fetchTop(params.type, params.subtype, params.page)
    }

    class Params(val type: String, val subtype: String, val page: Int)
}