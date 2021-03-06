package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import com.chun.domain.repository.AnimeRepository
import com.chun.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAnimeInfoUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) :
    FlowUseCase<GetAnimeInfoUseCase.Params, Anime>(coroutineDispatcher) {

    override fun execute(params: Params) =
        flow { emitAll(animeRepository.getInfo(params.id)) }.map { Resource.Success(it) }

    class Params(val id: Int)
}