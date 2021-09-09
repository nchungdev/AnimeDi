package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.Resource
import com.chun.domain.model.Season
import com.chun.domain.repository.HomeRepository
import com.chun.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchSeasonUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    FlowUseCase<FetchSeasonUseCase.Params, Season>(dispatcher) {

    override fun execute(params: Params) =
        flow { emit(homeRepository.fetchSeason(params.year, params.season)) }.map { Resource.Success(it) }

    class Params(val year: Int, val season: String)
}