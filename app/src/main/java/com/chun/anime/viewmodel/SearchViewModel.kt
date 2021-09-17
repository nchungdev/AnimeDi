package com.chun.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chun.anime.ui.widget.SearchEditText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel(), SearchEditText.OnQueryTextListener {
    private val _keyboardVisible = MutableLiveData<Boolean>()
    val keyboardVisible: LiveData<Boolean> get() = _keyboardVisible

    private val _keyword = MutableLiveData<String>()
    val keyword: LiveData<String> get() = _keyword

    fun toggleKeyboard(show: Boolean) {
        _keyboardVisible.postValue(show)
    }

    override fun onQueryTextSubmit(query: String) {
        _keyword.postValue(query)
    }

    override fun onQueryTextChange(newText: String) {
        _keyword.postValue(newText)
    }
}