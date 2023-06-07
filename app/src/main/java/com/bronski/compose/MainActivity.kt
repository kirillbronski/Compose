package com.bronski.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.bronski.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import net.datafaker.Faker
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var faker: Faker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    navigation(startDestination = "login", route = "auth") {
                        composable("login") {
                            val viewModel =
                                it.sharedViewModel<SampleViewModel>(navController = navController)
                            Button(onClick = {
                                navController.navigate("calendar"){
                                    popUpTo("auth"){
                                        inclusive = true
                                    }
                                }
                            }) {

                            }
                        }
                        composable("register") {
                            val viewModel =
                                it.sharedViewModel<SampleViewModel>(navController = navController)
                        }
                        composable("forgot_password") {
                            val viewModel =
                                it.sharedViewModel<SampleViewModel>(navController = navController)
                        }
                    }
                    navigation(
                        startDestination = "calendar_overview",
                        route = "calendar"
                    ) {
                        composable("calendar_overview") {

                        }
                        composable("calendar_entry") {

                        }
                    }
                    composable("about") {

                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
fun Greeting(name: String, image: String) {
    Text(text = "Hello $name!", textAlign = TextAlign.Center)
    AsyncImage(model = image, contentDescription = "Avatar")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        //Greeting("Android")
    }
}