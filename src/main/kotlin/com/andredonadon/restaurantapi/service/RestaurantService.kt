package com.andredonadon.restaurantapi.service

import com.andredonadon.restaurantapi.entity.DishEntity
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import com.andredonadon.restaurantapi.entity.key.RestaurantDishKey
import com.andredonadon.restaurantapi.entity.m2m.RestaurantDishEntity
import com.andredonadon.restaurantapi.repository.DishRepository
import com.andredonadon.restaurantapi.repository.RestaurantDishRepository
import com.andredonadon.restaurantapi.repository.RestaurantRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.UUID

@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository,
    private val dishRepository: DishRepository,
    private val restaurantDishRepository: RestaurantDishRepository
) {

    @PostConstruct
    fun initData() {
        val restaurants = listOf(
            RestaurantEntity(UUID.randomUUID(), "McDonald's", true),
            RestaurantEntity(UUID.randomUUID(), "Burger King", true),
            RestaurantEntity(UUID.randomUUID(), "KFC", true),
            RestaurantEntity(UUID.randomUUID(), "Subway", false),
            RestaurantEntity(UUID.randomUUID(), "Pizza Hut", true)
        )

        val dishes = listOf(
            DishEntity(UUID.randomUUID(), "Big Mac", true),
            DishEntity(UUID.randomUUID(), "McChicken", true),
            DishEntity(UUID.randomUUID(), "McNuggets", true),
            DishEntity(UUID.randomUUID(), "Whopper", true),
            DishEntity(UUID.randomUUID(), "Chicken Bucket", true),
            DishEntity(UUID.randomUUID(), "Pizza Pepperoni", true)
        )

        restaurantRepository.saveAll(restaurants).subscribe { restaurant ->
            dishRepository.saveAll(dishes).subscribe { dish ->
                val relation = RestaurantDishEntity(RestaurantDishKey(restaurant.id, dish.id))
                restaurantDishRepository.save(relation).subscribe()
            }
        }
    }

    fun getRestaurants(): Flux<RestaurantEntity> = restaurantRepository.findAll()

    fun getDishesByRestaurantEntity(restaurantId: UUID): Flux<DishEntity> =
        restaurantDishRepository.findByKeyRestaurantId(restaurantId)
            .flatMap { dishRepository.findById(it.key.dishId) }
}