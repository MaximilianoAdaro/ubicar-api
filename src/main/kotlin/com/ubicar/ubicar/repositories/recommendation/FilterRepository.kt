package com.ubicar.ubicar.repositories.recommendation

import com.ubicar.ubicar.entities.Recommendation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface RecommendationRepository : CrudRepository<Recommendation, String> {

  @Query(value = "select r from Recommendation r where r.filter.id = :#{#id}")
  fun findByFilter(id: String): List<Recommendation>

  @Query(value = "select r from Recommendation r where r.filter.user.id = :#{#id}")
  fun findByUser(id: String): List<Recommendation>

  @Query(value = "select r from Recommendation r where r.liked.id = :#{#id}")
  fun findByProperty(id: String): Recommendation?
}
