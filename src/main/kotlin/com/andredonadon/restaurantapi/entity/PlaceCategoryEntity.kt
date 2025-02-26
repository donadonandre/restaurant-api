package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("place_categories")
data class PlaceCategoryEntity(
    @Id val id: Int? = null,
    val description: String
)
