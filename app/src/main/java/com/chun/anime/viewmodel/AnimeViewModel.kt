package com.chun.anime.viewmodel

import androidx.lifecycle.*
import com.chun.domain.Resource
import com.chun.domain.model.Anime
import com.chun.domain.model.Otaku
import com.chun.domain.usecase.GetAnimeInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeInfoUseCase: GetAnimeInfoUseCase
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    val animeInfo: LiveData<Resource<Anime>>
        get() = Transformations.switchMap(_id) { id ->
            liveData {
                emit(Resource.Loading)
                getAnimeInfoUseCase(GetAnimeInfoUseCase.Params(id)).collect {
                    emit(it)
                }
            }
        }

    fun getInfo(otaku: Otaku) {
        _id.postValue(otaku.id)
    }
}