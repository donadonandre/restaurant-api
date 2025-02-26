package com.andredonadon.restaurantapi.dto

import java.util.UUID

data class RestaurantDTO(
    val id: UUID,
    val name: String,
    val review: String? = null
)
