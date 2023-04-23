package com.bronski.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    Button(
                        onClick = {
                            isVisible = !isVisible
                        })
                    {
                        Text(text = "Toggle")
                    }
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInHorizontally() + fadeIn(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Box(modifier = Modifier.background(Color.Red))
                    }
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