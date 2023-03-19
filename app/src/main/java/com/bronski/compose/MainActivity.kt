package com.bronski.compose

import android.Manifest.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.bronski.compose.ui.theme.ComposeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionsState = rememberMultiplePermissionsState(
                permissions = listOf(
                    permission.RECORD_AUDIO,
                    permission.CAMERA
                )
            )
            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(key1 = lifecycleOwner, effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionsState.launchMultiplePermissionRequest()
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            })
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                permissionsState.permissions.forEach { perm ->
                    when (perm.permission) {
                        permission.CAMERA -> {
                            when {
                                perm.status.isGranted -> {
                                    Text(text = "Camera permission accepted")
                                }
                                perm.status.shouldShowRationale -> {
                                    Text(text = "Camera permission is needed to access the camera")
                                }
                                perm.isPermanentlyDenied() -> {
                                    Text(text = "Camera permission was permanently denied. You can enable it in the app settings")
                                }
                            }
                        }
                        permission.RECORD_AUDIO -> {
                            when {
                                perm.status.isGranted -> {
                                    Text(text = "Record audio permission accepted")
                                }
                                perm.status.shouldShowRationale -> {
                                    Text(text = "Record audio permission is needed to access the dialer")
                                }
                                perm.isPermanentlyDenied() -> {
                                    Text(text = "Record audio permission was permanently denied. You can enable it in the app settings")
                                }
                            }
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