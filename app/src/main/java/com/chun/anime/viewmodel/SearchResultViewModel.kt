package com.chun.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chun.domain.Resource
import com.chun.domain.model.Otaku
import com.chun.domain.model.type.ObjType
import com.chun.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {
    private var currentType: String = ObjType.UNKNOWN

    val accept: (UiAction) -> Unit

    val state: LiveData<UiState>

    init {
        val actionStateFlow = MutableSharedFlow<Query>()

        val searches = actionStateFlow
            .filterNot { it.keyword.isEmpty() }
            .distinctUntilChanged()
            .debounce(300)
            .flatMapLatest { load(it) }
            .onStart { emit(UiState()) }

        state = searches.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState()
        )
            .asLiveData()

        accept = { action ->
            viewModelScope.launch {
                when (action) {
                    is UiAction.Typing -> {
                        //request
                        actionStateFlow.emit(Query.Changed(action.keyword))
                    }
                    is UiAction.Retry -> {
                        // retry
                        actionStateFlow.emit(Query.Changed(state.value?.currentQuery?.keyword ?: return@launch))
                    }
                    is UiAction.LoadMore -> {
                        val uiState = state.value ?: return@launch
                        val currentQuery = uiState.currentQuery.keyword
                        val nextPage = uiState.currentQuery.page
                        actionStateFlow.emit(Query.LoadMore(currentQuery, nextPage))
                    }
                    is UiAction.Filter -> Unit
                }
            }
        }
    }

    fun currentType(type: String) {
        currentType = type
    }

    private fun load(query: Query) =
        searchUseCase(SearchUseCase.Params(currentType, query.keyword, query.page)).map {
            val nextPage = query.page + (if (it is Resource.Success) 1 else 0)
            val isLoadMore = query is Query.LoadMore
            UiState(Query.Initial(query.keyword, nextPage), it, if (isLoadMore) LoadState.APPEND else LoadState.REFRESH)
        }

    sealed class UiAction {
        data class Typing(val keyword: String = "") : UiAction()
        data class Filter(val rated: String) : UiAction()
        object LoadMore : UiAction()
        object Retry : UiAction()
    }

    data class UiState(
        val currentQuery: Query = Query.Changed(""),
        val data: Resource<List<Otaku>> = Resource.Loading,
        val state: Int = LoadState.INITIAL
    )

    sealed class Query(val keyword: String, val page: Int) {
        class Initial(keyword: String, page: Int) : Query(keyword, page)
        class Changed(keyword: String) : Query(keyword, 1)
        class LoadMore(keyword: String, page: Int) : Query(keyword, page)
    }
}
