package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import com.chun.domain.repository.AnimeRepository
import com.chun.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchTopAnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) :
    FlowUseCase<FetchTopAnimeUseCase.Params, List<Anime>>(dispatcher) {

    override fun execute(params: Params): Flow<Resource<List<Anime>>> =
        flow { emitAll(animeRepository.fetchTop(params.subtype, params.page)) }.map { Resource.Success(it) }

    data class Params(val subtype: String = "", val page: Int = 1)
}