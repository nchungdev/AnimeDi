package com.chun.domain.usecase.base

import androidx.paging.PagingData
import com.chun.domain.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Resource.Error] to the result) is the subclasses's responsibility.
 */
abstract class PagingUseCase<in P, R : Any>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<PagingData<R>> = execute(parameters)
        .catch { e -> emit(PagingData.empty()) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(params: P): Flow<PagingData<R>>
}
