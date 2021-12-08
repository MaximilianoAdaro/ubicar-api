package com.ubicar.ubicar.services.recommendation

import com.ubicar.ubicar.entities.Recommendation

interface RecommendationService {

  fun save(recommendation: Recommendation): Recommendation

  fun findByFilter(id: String): List<Recommendation>

  fun getRecommendations(size: Int): List<Recommendation>

  fun recommendationsMail(recommendation: Recommendation)
}
