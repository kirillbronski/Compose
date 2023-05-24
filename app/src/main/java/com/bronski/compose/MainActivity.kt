package com.bronski.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bronski.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.datafaker.Faker
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var faker: Faker

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val images = listOf(
            R.drawable.grid_0,
            R.drawable.grid_01,
            R.drawable.grid_1220,
        )
        setContent {
            ComposeTheme {
                val pagerState = rememberPagerState()
                val scope = rememberCoroutineScope()
                Box(modifier = Modifier.fillMaxSize()) {
                    HorizontalPager(
                        pageCount = images.count(),
                        state = pagerState,
                        key = { images[it] },
                        pageSize = PageSize.Fill
                    ) { index ->
                        Image(
                            painter = painterResource(id = images[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = -(16.dp))
                            .fillMaxWidth(0.5f)
                            .clip(RoundedCornerShape(100))
                            .background(MaterialTheme.colors.background)
                            .padding(8.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage - 1
                                    )
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Go back"
                            )
                        }
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage + 1
                                    )
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Go forward"
                            )
                        }
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