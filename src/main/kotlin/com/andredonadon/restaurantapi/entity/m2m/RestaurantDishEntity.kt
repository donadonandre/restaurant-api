package com.andredonadon.restaurantapi.entity.m2m

import com.andredonadon.restaurantapi.entity.key.RestaurantDishKey
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("restaurant_dishes")
data class RestaurantDishEntity(
    @Id val key: RestaurantDishKey
)