package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.DishEntity
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.UUID

@Repository
interface RestaurantRepository : ReactiveCrudRepository<RestaurantEntity, UUID> {
    fun findByRestaurantId(restaurantId: UUID): Flux<DishEntity>
}