package com.chun.anime.viewmodel

import androidx.lifecycle.viewModelScope
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import com.chun.domain.model.type.Subtype
import com.chun.domain.usecase.FetchTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val fetchTopAnimeUseCase: FetchTopAnimeUseCase
) : LoadingViewModel() {

    val accept: (UiAction) -> Unit

    val upcoming: Flow<UiState>

    private var nextPage: Int = 1

    init {
        val actionStateFlow = MutableSharedFlow<UiAction>()

        val load = actionStateFlow
            .filterIsInstance<UiAction.Load>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Load(Subtype.UPCOMING, nextPage)) }

        val scroll = actionStateFlow
            .filterIsInstance<UiAction.Scroll>()
            .distinctUntilChanged()
            .map { nextPage }
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                replay = 1
            )

        upcoming = combine(scroll, load, ::Pair)
            .map {
                it.second.page = it.first
                it.second
            }
            .distinctUntilChanged()
            .flatMapLatest {
                fetchTopAnimeUseCase(FetchTopAnimeUseCase.Params(it.type, it.page))
            }
            .map {
                if (it is Resource.Success && it.data.isNotEmpty()) {
                    nextPage++
                }
                UiState(it, if (nextPage <= 1) LoadState.REFRESH else LoadState.APPEND)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = UiState()
            )

        accept = {
            viewModelScope.launch {
                actionStateFlow.emit(it)
            }
        }
    }

    sealed class UiAction {
        data class Load(val type: String, var page: Int) : UiAction()
        object Scroll : UiAction()
    }

    data class UiState(
        val data: Resource<List<Anime>> = Resource.Loading,
        val type: Int = LoadState.INITIAL
    )
}