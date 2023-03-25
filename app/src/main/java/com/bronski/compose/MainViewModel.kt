package com.bronski.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bronski.compose.data.local.ScreenState
import com.bronski.compose.domain.useCases.GetItemUseCase
import com.bronski.compose.paging.DefaultPaginator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val getItemUseCase = GetItemUseCase()

    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdate = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getItemUseCase.execute(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty(),
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}