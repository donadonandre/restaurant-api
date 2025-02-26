package com.andredonadon.restaurantapi.service

import com.andredonadon.restaurantapi.repository.DishRepository
import org.springframework.stereotype.Service

@Service
class DishService(
    private val repository: DishRepository
) {
}