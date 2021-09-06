package com.chun.anime.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.chun.domain.usecase.FetchHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchHomeUseCase: FetchHomeUseCase) : LoadingViewModel() {

    val homes = Transformations.switchMap(getData) { getData ->
        return@switchMap if (getData) fetchHomeUseCase(FetchHomeUseCase.Params()).asLiveData()
        else emptyLiveData()
    }
}
