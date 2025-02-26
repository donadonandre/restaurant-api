package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("dishes")
data class DishEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    val active: Boolean,
)
