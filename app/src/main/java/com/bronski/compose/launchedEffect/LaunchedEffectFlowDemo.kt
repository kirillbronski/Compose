package com.bronski.compose.launchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchedEffectViewModel
) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect {
            when (it) {
                is ScreenEvents.ShowSnackBar -> {

                }
                is ScreenEvents.Navigate -> {

                }
            }
        }
    }
}