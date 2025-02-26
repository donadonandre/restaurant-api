package com.andredonadon.restaurantapi.entity.key

import java.io.Serializable
import java.util.*

data class RestaurantDishKey(
    val restaurantId: UUID,
    val dishId: UUID
) : Serializable