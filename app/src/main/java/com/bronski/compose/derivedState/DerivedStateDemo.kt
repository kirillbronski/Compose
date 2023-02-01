package com.bronski.compose.derivedState

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedStateDemo() {
    var counter by remember {
        mutableStateOf(0)
    }
//    val counterText = "The counter is $counter"
    val counterText by derivedStateOf {
        "The counter is $counter"
    }
    Button(onClick = {
        counter++
    }) {
        Text(text = counterText)
    }
}