package com.chun.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.chun.anime.util.Connection
import com.chun.anime.util.ConnectionStateManager
import timber.log.Timber

abstract class LoadingViewModel : ViewModel(), ConnectionStateManager.Callback {
    private val _getData = MutableLiveData<Boolean>()
    val getData: LiveData<Boolean> get() = _getData

    private val _refreshVisible = MutableLiveData<Boolean>()
    val refreshVisible: LiveData<Boolean> get() = _refreshVisible

    init {
        Connection.registerCallback(this)
        _getData.postValue(true)
    }

    protected var error = false

    override fun onCleared() {
        Connection.unregisterCallback(this)
        super.onCleared()
    }

    override fun onChange(connected: Boolean) {
        if (connected && error) {
            error = false
            _getData.postValue(connected)
            Timber.e("Retry it")
        }
    }

    fun refresh() {
        _getData.postValue(true)
    }

    fun toggleRefresh(visible: Boolean) {
        _refreshVisible.postValue(visible)
    }

    protected fun <T> emptyLiveData() = liveData<T> {}
}