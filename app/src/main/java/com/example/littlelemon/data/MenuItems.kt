package com.example.littlelemon.data

import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)

@Serializable
data class MenuItems(
    val menu: List<MenuItem>
)
