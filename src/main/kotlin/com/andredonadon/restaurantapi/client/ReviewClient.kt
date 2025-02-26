package com.andredonadon.restaurantapi.client

import com.andredonadon.restaurantapi.dto.response.RestaurantClientResponseDTO
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Component
class ReviewClient(
    private val webClient: WebClient
) {

    fun getReviewFromReviewService(restaurantId: UUID): Mono<String> {
        return webClient.get()
            .uri("http://review-service/api/v1/review/$restaurantId")
            .retrieve()
            .bodyToMono(RestaurantClientResponseDTO::class.java)
            .map { review ->
                "${review.totalReviews} (${review.averageRating})"
            }
            .onErrorReturn("0 (0.0)")
    }
}