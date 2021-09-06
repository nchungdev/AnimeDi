package com.chun.data.util

import com.chun.domain.Resource
import com.chun.domain.model.CacheVersion
import kotlinx.coroutines.flow.*
import timber.log.Timber

object NetworkBoundResource {
    operator fun <ResultType, RequestType> invoke(
        localFetch: () -> Flow<CacheVersion<ResultType>?>,
        remoteFetch: suspend () -> RequestType,
        storeRemoteResult: suspend (RequestType) -> Unit,
        shouldFetch: (CacheVersion<ResultType>?) -> Boolean = { it?.data == null || it.isExpired() }
    ) = flow {
        emit(Resource.Loading)
        val (cache, throwable) = getFromCache(localFetch)
        if (!shouldFetch(cache.firstOrNull())) {
            emitAll(process(cache, throwable))
        }
        val api = fetchApi(remoteFetch)
        if (api == null) {
            emitAll(process(cache, throwable))
        } else {
            storeRemoteResult(api)
            val (flow, throwable) = getFromCache(localFetch)
            emitAll(process(flow, throwable))
        }
    }

    private fun <ResultType> getFromCache(query: () -> Flow<ResultType?>): Pair<Flow<ResultType?>, Throwable?> =
        try {
            Pair(query(), null)
        } catch (e: Exception) {
            Pair(emptyFlow(), e)
        }

    private suspend fun <RequestType> fetchApi(fetch: suspend () -> RequestType): RequestType? = try {
        fetch()
    } catch (e: Exception) {
        null
    }

    private fun <ResultType> process(cache: Flow<CacheVersion<ResultType>?>, e: Throwable?) =
        if (e == null) {
            cache.map {
                if (it?.data != null) {
                    Timber.e("Otaku ${it.data}")
                    Resource.Success(it.data!!)
                } else {
                    Resource.Error(NullPointerException("No Cache"))
                }
            }
        } else
            flowOf(Resource.Error(e))
}