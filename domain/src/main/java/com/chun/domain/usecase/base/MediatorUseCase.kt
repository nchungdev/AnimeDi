package com.chun.domain.usecase.base

import androidx.lifecycle.MediatorLiveData
import com.chun.domain.Resource

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Resource.Error] to the result) is the subclasses's responsibility.
 */
abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Resource<R>>()

    // Make this as open so that mock instances can mock this method
    open fun observe(): MediatorLiveData<Resource<R>> {
        return result
    }

    abstract fun execute(params: P)
}
