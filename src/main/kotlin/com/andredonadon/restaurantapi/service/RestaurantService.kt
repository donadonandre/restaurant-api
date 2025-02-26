package com.andredonadon.restaurantapi.service

import com.andredonadon.restaurantapi.client.ReviewClient
import com.andredonadon.restaurantapi.dto.DishDTO
import com.andredonadon.restaurantapi.dto.RestaurantDTO
import com.andredonadon.restaurantapi.dto.request.RestaurantRequestDTO
import com.andredonadon.restaurantapi.entity.RestaurantEntity
import com.andredonadon.restaurantapi.repository.DishRepository
import com.andredonadon.restaurantapi.repository.RestaurantDishRepository
import com.andredonadon.restaurantapi.repository.RestaurantRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.util.*

@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository,
    private val dishRepository: DishRepository,
    private val restaurantDishRepository: RestaurantDishRepository,
    private val reviewClient: ReviewClient
) {

    fun getRestaurants(cityId: BigInteger, page: Int, size: Int): Flux<RestaurantEntity> {
        val offset = page * size
        return restaurantRepository.findByCityId(cityId, size, offset)
    }

    fun getRestaurantsByCategory(cityId: BigInteger, placeCategoryId: Int, page: Int, size: Int): Flux<RestaurantEntity> {
        val offset = page * size
        return restaurantRepository.findByCityIdAndPlaceCategoryId(cityId, placeCategoryId, size, offset)
    }

    fun getDishesByRestaurant(restaurantId: UUID): Flux<DishDTO> {
        return restaurantDishRepository.findByKeyRestaurantId(restaurantId)
            .flatMap { restaurantDish ->
                dishRepository.findById(restaurantDish.key.dishId)
                    .map { dish -> DishDTO(dish.id, dish.name, dish.dishCategoryId) }
            }
            .collectList()
            .map { dishes ->
                dishes.sortedWith(compareBy<DishDTO> { it.categoryId }.thenBy { it.name })
            }
            .flatMapMany { Flux.fromIterable(it) }
    }

    fun createRestaurant(request: RestaurantRequestDTO): Mono<RestaurantDTO> = mono {
        val restaurant = RestaurantEntity(
            name = request.name,
            cityId = request.cityId,
            placeCategoryId = request.placeCategoryId
        )

        val savedRestaurant = restaurantRepository.save(restaurant).block()!!

        return@mono RestaurantDTO(
            id = savedRestaurant.id,
            name = savedRestaurant.name
        )
    }

    suspend fun getRestaurantById(restaurantId: UUID): Mono<RestaurantDTO> = mono {
        coroutineScope {
            val restaurantDeferred = async {
                restaurantRepository.findById(restaurantId)
                    .switchIfEmpty(
                        Mono.error(
                            ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Restaurante não encontrado"
                            )
                        )
                    )
                    .block()
            }

            val reviewDeferred = async {
                reviewClient.getReviewFromReviewService(restaurantId)
                    .onErrorReturn("0 (0.0)")
                    .block()
            }

            val restaurant = restaurantDeferred.await()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado")

            val review = reviewDeferred.await()

            return@coroutineScope RestaurantDTO(
                id = restaurant.id,
                name = restaurant.name,
                review = review ?: "0 (0.0)"
            )
        }
    }
}