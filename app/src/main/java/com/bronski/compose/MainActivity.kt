package com.bronski.compose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bronski.compose.ui.theme.ComposeTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val viewModel = viewModel<MainViewModel>()
                val isLoading by viewModel.isLoading.collectAsState()
                val refreshing by viewModel.isLoading.collectAsState()
                //DeprecatedSwipeRefresh(isLoading = isLoading, viewModel = viewModel)
                PullRefreshExample(refreshing = refreshing, viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshExample(
    refreshing: Boolean,
    viewModel: MainViewModel
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = viewModel::loadStuff
    )
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) {
                Text(
                    text = "Test",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                )
            }
        }
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            Modifier
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun DeprecatedSwipeRefresh(
    isLoading: Boolean,
    viewModel: MainViewModel
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::loadStuff
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) {
                Text(
                    text = "Test",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                )
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