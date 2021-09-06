package com.chun.data.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chun.data.remote.service.SearchService
import com.chun.domain.model.Otaku
import com.chun.domain.param.SearchParams
import retrofit2.HttpException
import java.io.IOException


class SearchPagingDataSource(private val service: SearchService, private val query: SearchParams) :
    PagingSource<Int, Otaku>() {

    override fun getRefreshKey(state: PagingState<Int, Otaku>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Otaku> {
        query.page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.search(query.type, query.toMap())
            val list = response.data ?: return LoadResult.Error(NullPointerException("Not Found Keyword"))
            list.forEach { it.type = query.type }
            val nextKey = if (list.isEmpty()) null else query.page.inc()
            LoadResult.Page(
                data = list,
                prevKey = if (query.page == STARTING_PAGE_INDEX) null else query.page - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}