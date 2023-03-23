package com.bronski.compose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bronski.compose.destinations.PostScreenDestination
import com.bronski.compose.destinations.ProfileScreenDestination
import com.bronski.compose.ui.theme.ComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
//            val navController = rememberNavController()
//            NavHost(
//                navController = navController,
//                startDestination = "login"
//            ) {
//                composable("login") {
//                    LoginScreen(navController)
//                }
//                composable(
//                    route = "profile/{name}/{userId}/{timestamp}",
//                    arguments = listOf(
//                        navArgument("name") {
//                            type = NavType.StringType
//                        },
//                        navArgument("userId") {
//                            type = NavType.StringType
//                        },
//                        navArgument("timestamp") {
//                            type = NavType.LongType
//                        },
//                    )
//                ) {
//                    val name = it.arguments?.getString("name")!!
//                    val userId = it.arguments?.getString("userId")!!
//                    val timestamp = it.arguments?.getLong("timestamp")!!
//
//                    ProfileScreen(
//                        navController = navController,
//                        name = name,
//                        userId = userId,
//                        created = timestamp
//                    )
//                }
//                composable("post/{showOnlyPostsByUser}", arguments = listOf(
//                    navArgument("showOnlyPostsByUser") {
//                        type = NavType.BoolType
//                        defaultValue = false
//                    }
//                )) {
//                    val showOnlyPostsByUser =
//                        it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
//                    PostScreen(showOnlyPostsByUser)
//                }
//            }
//        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navigator.navigate(ProfileScreenDestination(User("1", "Kirill", LocalDateTime.now())))
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    user: User
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navigator.navigate(PostScreenDestination())
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Destination
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}