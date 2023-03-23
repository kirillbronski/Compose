package com.bronski.compose



import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class User(
    val id: String,
    val name: String,
    val created: LocalDateTime
): Parcelable
