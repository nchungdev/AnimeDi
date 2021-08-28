package com.chun.anime.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chun.anime.util.UiAction
import com.chun.anime.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel(), SearchView.OnQueryTextListener {
    private val _keyboardVisible = MutableLiveData<Boolean>()
    val keyboardVisible: LiveData<Boolean> get() = _keyboardVisible

    private val _keyword = MutableLiveData<String>()
    val keyword: LiveData<String> get() = _keyword

    override fun onQueryTextSubmit(query: String?): Boolean {
        _keyword.postValue(query ?: return false)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        _keyword.postValue(newText ?: return false)
        return false
    }

    fun toggleKeyboard(show: Boolean) {
        _keyboardVisible.postValue(show)
    }
}