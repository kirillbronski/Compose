package com.bronski.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController


@Composable
fun SharedViewModelSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "onboarding"
    ) {
        navigation(
            startDestination = "personal_details", route = "onboarding"
        ) {
            composable("personal_details") {
                val viewModel = it.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsState()

                PersonalDetailScreen(
                    sharedState = state,
                    onNavigate = {
                        viewModel.updateState()
                        navController.navigate("terms_and_conditions")
                    }
                )
            }
            composable("terms_and_conditions") {
                val viewModel = it.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsState()

                TermsAndConditionsScreen(
                    sharedState = state,
                    onBoardingFinished = {
                        navController.navigate(route = "other_screen") {
                            popUpTo("onboarding") {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable("other_screen"){
            Text(text = "Hello world")
        }
    }
}

@Composable
inline fun<reified T : ViewModel> NavBackStackEntry.sharedViewModel (
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
private fun PersonalDetailScreen (
    sharedState: Int,
    onNavigate: () -> Unit
) {
    Button(onClick = onNavigate) {
        Text(text = "Click me")
    }
}

@Composable
private fun TermsAndConditionsScreen (
    sharedState: Int,
    onBoardingFinished: () -> Unit
) {
    Button(onClick = onBoardingFinished) {
        Text(text = "State: $sharedState")
    }
}