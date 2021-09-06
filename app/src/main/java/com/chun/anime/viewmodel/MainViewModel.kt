package com.chun.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _toggleSearchScreen = MutableLiveData<Boolean>()
    val toggleSearchScreen: LiveData<Boolean> get() = _toggleSearchScreen

    fun toggleSearchScreen(visible: Boolean) = _toggleSearchScreen.postValue(visible)
}