package com.chun.anime.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.chun.domain.usecase.FetchHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchHomeUseCase: FetchHomeUseCase) : LoadingViewModel() {
    private lateinit var type: String

    fun initType(type: String) {
        this.type = type
    }

    val homes by lazy {
        Transformations.switchMap(getData) { getData ->
            return@switchMap if (getData) fetchHomeUseCase(FetchHomeUseCase.Params(type)).asLiveData()
            else emptyLiveData()
        }
    }
}
