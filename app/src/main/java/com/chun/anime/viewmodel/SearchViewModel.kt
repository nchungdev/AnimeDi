package com.chun.anime.viewmodel

import androidx.lifecycle.ViewModel
import com.chun.domain.usecase.FetchTopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(fetchTopUseCase: FetchTopUseCase) : ViewModel() {

}