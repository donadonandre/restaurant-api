package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.RestaurantEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.math.BigInteger
import java.util.*

@Repository
interface RestaurantRepository : ReactiveCrudRepository<RestaurantEntity, UUID> {
    @Query("SELECT * FROM restaurants WHERE city_id = :cityId LIMIT :limit OFFSET :offset")
    fun findByCityId(cityId: BigInteger, limit: Int, offset: Int): Flux<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE city_id = :cityId AND place_category_id = :placeCategoryId LIMIT :limit OFFSET :offset")
    fun findByCityIdAndPlaceCategoryId(cityId: BigInteger, placeCategoryId: Int, limit: Int, offset: Int): Flux<RestaurantEntity>
}