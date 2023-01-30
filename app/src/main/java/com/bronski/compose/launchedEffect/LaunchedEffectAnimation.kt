package com.bronski.compose.launchedEffect

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@SuppressLint("RememberReturnType")
@Composable
fun LaunchedEffectAnimation(
    counter: Int
) {
    val animate = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animate.animateTo(counter.toFloat())
    }
}