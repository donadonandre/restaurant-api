package com.andredonadon.restaurantapi.dto.request

import java.math.BigInteger

data class RestaurantRequestDTO(
    val name: String,
    val cityId: BigInteger,
    val placeCategoryId: Int
)