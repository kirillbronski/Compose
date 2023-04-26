package com.bronski.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bronski.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import net.datafaker.Faker
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var faker: Faker

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    var isRound by remember {
                        mutableStateOf(false)
                    }
                    Button(
                        onClick = {
                            isVisible = !isVisible
                            isRound = !isRound
                        })
                    {
                        Text(text = "Toggle")
                    }
                    //val updateTransition = updateTransition(targetState = isRound, label = null)
//                    val borderRadius by animateIntAsState(
//                        targetValue = if (isRound) 100 else 0,
//                        animationSpec = tween(
//                            durationMillis = 1000,
//                            delayMillis = 100
//                        )
//                    )
//                    val borderRadius by updateTransition.animateInt(
//                        transitionSpec = {
//                            tween(durationMillis = 2000,)
//                        }, label = "",
//                        targetValueByState = { isRound ->
//                            if (isRound) 100 else 0
//                        }
//                    )
//                    val color by updateTransition.animateColor(
//                        transitionSpec = {
//                            tween(durationMillis = 2000,)
//                        }, label = "",
//                        targetValueByState = { isRound ->
//                            if (isRound) Color.Green else Color.Red
//                        }
//                    )
//                    val transition = rememberInfiniteTransition()
//                    val color by transition.animateColor(
//                        initialValue = Color.Red,
//                        targetValue = Color.Green,
//                        animationSpec = infiniteRepeatable(
//                            animation = tween(2000),
//                            repeatMode = RepeatMode.Reverse
//                        )
//                    )
//                    Box(modifier = Modifier
//                        .size(200.dp)
//                        //.clip(RoundedCornerShape(borderRadius))
//                        .background(color)
//                    )
//                    AnimatedVisibility(
//                        visible = isVisible,
//                        enter = slideInHorizontally() + fadeIn(),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                        Box(modifier = Modifier.background(Color.Red))
//                    }
                    AnimatedContent(targetState = isVisible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        content = { isVisible ->
                            if (isVisible) {
                                Box(modifier = Modifier.background(Color.Green))
                            } else {
                                Box(modifier = Modifier.background(Color.Red))
                            }
                        },
                        transitionSpec = {
                            //fadeIn() with fadeOut()
                            slideInHorizontally(
                                initialOffsetX = {
                                    if (isVisible) it else -it
                                }
                            ) with slideOutHorizontally(
                                targetOffsetX = {
                                    if (isVisible) -it else it
                                }
                            )
                        }
                    )
                }
            }
        }
    }
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