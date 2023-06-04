package com.bronski.compose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import coil.compose.AsyncImage
import com.bronski.compose.playback.AudioPlayerImpl
import com.bronski.compose.recorder.AudioRecorderImpl
import com.bronski.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import net.datafaker.Faker
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AudioRecorderImpl(applicationContext)
    }

    private val player by lazy {
        AudioPlayerImpl(applicationContext)
    }

    private var audiFile: File? = null

    @Inject
    lateinit var faker: Faker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            ComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        File(cacheDir, "audio.mp3").also {
                            recorder.start(it)
                            audiFile = it
                        }
                    }) {
                        Text(text = "Start recording")
                    }
                    Button(onClick = {
                        recorder.stop()
                    }) {
                        Text(text = "Stop recording")
                    }
                    Button(onClick = {
                        player.playFile(audiFile ?: return@Button)
                    }) {
                        Text(text = "Play")
                    }
                    Button(onClick = {
                        player.stop()
                    }) {
                        Text(text = "Stop")
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