package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("dish_categories")
data class DishCategoryEntity(
    @Id val id: Int? = null,
    val description: String
)
