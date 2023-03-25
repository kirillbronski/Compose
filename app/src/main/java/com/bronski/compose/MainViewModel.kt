package com.bronski.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bronski.compose.data.local.ScreenState
import com.bronski.compose.domain.useCases.GetItemUseCase
import com.bronski.compose.paging.DefaultPaginator

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

}