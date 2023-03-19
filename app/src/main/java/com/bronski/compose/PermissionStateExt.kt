package com.bronski.compose

import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !status.isGranted && !status.shouldShowRationale
}

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionStatus.isPermanentlyDenied(): Boolean {
    return !isGranted && !shouldShowRationale
}