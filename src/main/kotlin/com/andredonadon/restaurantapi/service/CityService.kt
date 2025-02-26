package com.andredonadon.restaurantapi.service

import com.andredonadon.restaurantapi.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(
    private val repository: CityRepository
) {
}