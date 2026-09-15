package com.example.s8130002Application2.data.model
import com.example.s8130002Application2.models.Instructor
data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

data class Entity(
    val property1: String,
    val property2: String,
    val description: String
)
