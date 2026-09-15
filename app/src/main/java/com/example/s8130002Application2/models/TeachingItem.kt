package com.example.s8130002Application2.models

import androidx.annotation.DrawableRes

data class TeachingItem(
    val id: Int,
    val title: String,
    val category: String,
    val rating: Float,
    @DrawableRes val imageUrl: Int
)
