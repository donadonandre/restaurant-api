package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("dish_categories")
data class DishCategoryEntity(
    @Id val id: BigInteger? = null,
    val description: String
)
