package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.PlaceCategoryEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface PlaceCategoryRepository : ReactiveCrudRepository<PlaceCategoryEntity, BigInteger>