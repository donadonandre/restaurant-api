package com.andredonadon.restaurantapi.controller

import com.andredonadon.restaurantapi.entity.DishEntity
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import com.andredonadon.restaurantapi.service.RestaurantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.UUID

@RestController
@RequestMapping("api/v1/restaurants")
class RestaurantController(private val restaurantService: RestaurantService) {

    @GetMapping
    fun getRestaurants(): Flux<RestaurantEntity> = restaurantService.getRestaurants()

    @GetMapping("/{restaurantId}/dishes")
    fun getDishes(@PathVariable restaurantId: UUID): Flux<DishEntity> =
        restaurantService.getDishesByRestaurant(restaurantId)
}