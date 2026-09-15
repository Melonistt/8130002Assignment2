package com.example.s8130002Application2.models

import androidx.annotation.DrawableRes

data class Instructor(
    val id: Int,
    val name: String,
    val specialty: String,
    val description: String,
    @DrawableRes val profileImageUrl: Int
)