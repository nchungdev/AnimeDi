package com.chun.anime.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val onQueryTextListener: SearchView.OnQueryTextListener,
) : SearchView.OnQueryTextListener {
    var debouncePeriod: Long = 300

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextListener.onQueryTextSubmit(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                onQueryTextListener.onQueryTextChange(newText)
            }
        }
        return false
    }
}