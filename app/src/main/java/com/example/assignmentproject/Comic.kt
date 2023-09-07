package com.example.assignmentproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Comic(
    val name: String,
    val description: String,
    val photo: String,
    val author: String,
    val artist: String,
    val genres: String,
    val review: String,
    val rating: String
) : Parcelable
