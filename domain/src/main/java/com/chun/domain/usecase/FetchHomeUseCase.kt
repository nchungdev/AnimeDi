package com.chun.domain.usecase

import com.chun.domain.IoDispatcher
import com.chun.domain.model.Home
import com.chun.domain.repository.FirestoreRepository
import com.chun.domain.repository.HomeRepository
import com.chun.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchHomeUseCase @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val homeRepository: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    FlowUseCase<FetchHomeUseCase.Params, List<Home>>(ioDispatcher) {

    override fun execute(params: Params) = flow {
        emitAll(homeRepository.getHome(firestoreRepository.getHomeLayout()))
    }

    class Params
}
