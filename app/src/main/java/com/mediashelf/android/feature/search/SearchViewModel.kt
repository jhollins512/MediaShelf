package com.mediashelf.android.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mediashelf.android.feature.search.domain.SearchAllMediaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val searchAllMedia: SearchAllMediaUseCase) : ViewModel() {

    private var searchJob: Job? = null

    private val searchQuery = MutableSharedFlow<String>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults = searchQuery.flatMapLatest {
        searchAllMedia(it)
    }.cachedIn(viewModelScope)

    fun searchMedia(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchQuery.emit(query)
        }
    }
}