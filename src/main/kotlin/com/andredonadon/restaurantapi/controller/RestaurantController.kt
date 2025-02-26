package com.andredonadon.restaurantapi.controller

import com.andredonadon.restaurantapi.dto.DishDTO
import com.andredonadon.restaurantapi.dto.RestaurantDTO
import com.andredonadon.restaurantapi.dto.request.RestaurantRequestDTO
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import com.andredonadon.restaurantapi.service.RestaurantService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.util.*

@RestController
@RequestMapping("api/v1/restaurants")
class RestaurantController(private val restaurantService: RestaurantService) {

    @GetMapping
    fun getRestaurants(
        @RequestHeader("city_id") cityId: BigInteger,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Flux<RestaurantEntity> {
        return restaurantService.getRestaurants(cityId, page, size)
    }

    @GetMapping("/{place_category_id}")
    fun getRestaurantsByCategory(
        @RequestHeader("city_id") cityId: BigInteger,
        @PathVariable("place_category_id") placeCategoryId: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Flux<RestaurantEntity> {
        return restaurantService.getRestaurantsByCategory(cityId, placeCategoryId, page, size)
    }

    @GetMapping("/{restaurantId}/dishes")
    fun getDishes(@PathVariable restaurantId: UUID): Flux<DishDTO> =
        restaurantService.getDishesByRestaurant(restaurantId)

    @PostMapping
    fun createRestaurant(@RequestBody request: RestaurantRequestDTO): Mono<RestaurantDTO> {
        return restaurantService.createRestaurant(request)
    }

    @GetMapping("/{restaurantId}")
    suspend fun getRestaurant(@PathVariable restaurantId: UUID): Mono<RestaurantDTO> {
        return restaurantService.getRestaurantById(restaurantId)
    }
}