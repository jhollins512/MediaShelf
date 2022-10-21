package com.jamaalhollins.movieshelf.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jamaalhollins.movieshelf.feature.search.domain.SearchAllMediaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(private val searchAllMedia: SearchAllMediaUseCase) : ViewModel() {

    private val searchQuery = MutableSharedFlow<String>()

    val searchResults = searchQuery.flatMapLatest {
        searchAllMedia(it)
    }.cachedIn(viewModelScope)

    fun searchMedia(query: String) {
        viewModelScope.launch {
            searchQuery.emit(query)
        }
    }
}