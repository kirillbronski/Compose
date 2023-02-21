package com.bronski.compose.util

import com.bronski.compose.R
import com.bronski.compose.data.local.BottomMenuContent
import com.bronski.compose.data.local.Feature
import com.bronski.compose.ui.theme.*

fun itemList() = listOf(
    Feature(title = "Sleep Meditation", R.drawable.ic_headphone, BlueViolet1, BlueViolet2, BlueViolet3),
    Feature(title = "Tips for sleeping", R.drawable.ic_videocam, LightGreen1, LightGreen2, LightGreen3),
    Feature(title = "Night island", R.drawable.ic_headphone, OrangeYellow1, OrangeYellow2, OrangeYellow3),
    Feature(title = "Calming sounds", R.drawable.ic_headphone, Beige1, Beige2, Beige3),
)

fun bottomMenuItemList() = listOf(
    BottomMenuContent(title = "Home", iconId = R.drawable.ic_home),
    BottomMenuContent(title = "Meditate", iconId = R.drawable.ic_bubble),
    BottomMenuContent(title = "Sleep", iconId = R.drawable.ic_moon),
    BottomMenuContent(title = "Music", iconId = R.drawable.ic_music),
    BottomMenuContent(title = "Profile", iconId = R.drawable.ic_profile),
)