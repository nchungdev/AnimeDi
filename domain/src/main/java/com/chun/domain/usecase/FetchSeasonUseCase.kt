package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.model.Season
import com.chun.domain.repository.HomeRepository
import com.chun.domain.usecase.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchSeasonUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    CoroutineUseCase<FetchSeasonUseCase.Params, Season>(dispatcher) {

    class Params(val year: Int, val season: String)

    override suspend fun execute(params: Params): Season {
        return homeRepository.fetchSeason(params.year, params.season)
    }
}