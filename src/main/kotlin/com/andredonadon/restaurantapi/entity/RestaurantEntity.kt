package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger
import java.util.UUID

@Table("restaurants")
data class RestaurantEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    val active: Boolean? = true,
    val cityId: BigInteger,
    val placeCategoryId: Int
)
