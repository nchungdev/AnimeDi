package com.chun.anime.util

import androidx.paging.PagingData
import com.chun.domain.model.Otaku
import com.chun.domain.param.SearchParams

sealed class UiAction {
    data class Search(val query: SearchParams) : UiAction()
    data class Scroll(val currentQuery: SearchParams) : UiAction()
}

data class UiState(
    val query: SearchParams = SearchParams(),
    val currentQuery: SearchParams = SearchParams(),
    val hasNotScrolledForCurrentSearch: Boolean = false,
    val pagingData: PagingData<Otaku> = PagingData.empty()
)
