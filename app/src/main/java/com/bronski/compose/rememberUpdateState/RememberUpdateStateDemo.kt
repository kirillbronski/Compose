package com.bronski.compose.rememberUpdateState

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun RememberUpdateStateDemo(
    onTimeout: () -> Unit
) {
    val updateOnTimeout by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(key1 = true){
        delay(3000)
        onTimeout()
    }

}