package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.DishEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DishRepository : ReactiveCrudRepository<DishEntity, UUID> {
    fun findByRestaurantId(restaurantId: UUID): MutableList<DishEntity>
}