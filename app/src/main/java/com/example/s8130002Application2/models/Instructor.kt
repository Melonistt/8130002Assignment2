package com.example.s8130002Application2.models

import androidx.annotation.DrawableRes

data class Instructor(
    val id: Int,
    val name: String,
    val followers: Int,
    @DrawableRes val profileImageUrl: Int
)