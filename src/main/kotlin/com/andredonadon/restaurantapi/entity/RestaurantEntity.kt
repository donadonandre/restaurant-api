package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("restaurants")
data class RestaurantEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    val review: String? = null,
    val active: Boolean? = true
)
