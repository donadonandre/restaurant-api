package com.andredonadon.restaurantapi.service

import com.andredonadon.restaurantapi.entity.DishEntity
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import com.andredonadon.restaurantapi.repository.DishRepository
import com.andredonadon.restaurantapi.repository.RestaurantDishRepository
import com.andredonadon.restaurantapi.repository.RestaurantRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository,
    private val dishRepository: DishRepository,
    private val restaurantDishRepository: RestaurantDishRepository
) {

    fun getRestaurants(): Flux<RestaurantEntity> = restaurantRepository.findAll()

    fun getDishesByRestaurantEntity(restaurantId: UUID): Flux<DishEntity> =
        restaurantDishRepository.findByKeyRestaurantId(restaurantId)
            .flatMap { dishRepository.findById(it.key.dishId) }
}