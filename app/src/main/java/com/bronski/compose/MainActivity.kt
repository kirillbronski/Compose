package com.bronski.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bronski.compose.data.local.model.Item
import com.bronski.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var items by remember {
                mutableStateOf(
                    (1..30).map {
                        Item(
                            title = "Item $it",
                            isSelected = false
                        )
                    }
                )
            }
            items.filter { it.isSelected }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size) { i ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            items = items.mapIndexed { j, item ->
                                if (i == j) {
                                    item.copy(isSelected = !item.isSelected)
                                } else item
                            }
                        }
                        .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[i].title)
                        if (items[i].isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Check",
                                tint = Color.Green,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
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