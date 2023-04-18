package com.bronski.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val list = (1..50).map {
                    faker.name().firstName()
                }
                val dropItem = (1..4).map {
                    DropDownItem(faker.animal().name())
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(list) {
                        PersonItem(
                            personName = it,
                            dropDownItems = dropItem,
                            onItemClick = {
                                Toast.makeText(applicationContext, it.text, Toast.LENGTH_SHORT).show()
                            })
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