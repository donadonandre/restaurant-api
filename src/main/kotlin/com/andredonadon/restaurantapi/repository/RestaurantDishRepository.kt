package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.key.RestaurantDishKey
import com.andredonadon.restaurantapi.entity.m2m.RestaurantDishEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface RestaurantDishRepository : ReactiveCrudRepository<RestaurantDishEntity, RestaurantDishKey> {
    fun findByKeyRestaurantId(restaurantId: UUID): Flux<RestaurantDishEntity>
}