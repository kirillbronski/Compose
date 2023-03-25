package com.bronski.compose.data.local

import com.bronski.compose.data.remote.model.ListItem

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ListItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
