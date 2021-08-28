package com.chun.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chun.anime.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _navigationItemSelected = MutableLiveData<Int>()
    val navigationItemSelected: LiveData<Int> = _navigationItemSelected

    init {
        _navigationItemSelected.value = R.id.navigation_home
    }

    fun navigate(id: Int) {
        _navigationItemSelected.value = id
    }
}