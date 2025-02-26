package com.andredonadon.restaurantapi.dto

import java.util.UUID

data class DishDTO(
    val id: UUID,
    val name: String,
    val categoryId: Int
)
