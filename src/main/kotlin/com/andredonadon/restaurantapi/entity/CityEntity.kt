package com.andredonadon.restaurantapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("cities")
data class CityEntity(
    @Id val id: BigInteger? = null,
    val name: String,
    val state: String,
)
