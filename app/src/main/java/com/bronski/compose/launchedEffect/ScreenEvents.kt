package com.bronski.compose.launchedEffect

sealed class ScreenEvents {
    data class ShowSnackBar(val message: String): ScreenEvents()
    data class Navigate(val route: String): ScreenEvents()
}
