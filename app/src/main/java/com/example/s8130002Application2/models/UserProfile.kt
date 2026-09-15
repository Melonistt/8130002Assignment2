package com.example.s8130002Application2.models

data class UserProfile(
    val name: String,
    val rating: Float,
    val reviews: Int,
    val followers: Int,
    val following: Int,
    val bio: String,
    val profileImageUrl: String,
    val skills: List<SkillItem>,
    val achievements: List<String>,
    val teaching: List<TeachingItem>,
    val learning: List<TeachingItem>
)
