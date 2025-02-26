package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("place_categories")
data class PlaceCategoryEntity(
    @Id val id: BigInteger? = null,
    val description: String
)
