package com.andredonadon.restaurantapi.repository

import com.andredonadon.restaurantapi.entity.DishCategoryEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface DishCategoryRepository : ReactiveCrudRepository<DishCategoryEntity, BigInteger>