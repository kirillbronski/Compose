package com.bronski.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bronski.compose.util.Screen1ViewModel

@Composable
fun StatefulDependencySample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<Screen1ViewModel>()
            val count by viewModel.count.collectAsState()

            Screen1(
                count = count,
                onNavigateToScreen2 = {
                    viewModel.inc()
                    navController.navigate("screen2")
                }
            )
        }
        composable(
            route = "screen2"
        ) {
            val viewModel = hiltViewModel<Screen1ViewModel>()
            val count by viewModel.count.collectAsState()

            Screen2(count)
        }
    }
}

@Composable
private fun Screen1(
    count: Int,
    onNavigateToScreen2: () -> Unit
) {
    Button(onClick = {
        onNavigateToScreen2()
    }) {
        Text(text = "Count on screen1: $count")
    }
}

@Composable
private fun Screen2(count: Int) {
    Text(text = "Count on screen2: $count")
}