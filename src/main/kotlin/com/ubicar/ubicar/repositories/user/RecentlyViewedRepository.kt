package com.ubicar.ubicar.repositories.user

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.RecentlyViewed
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecentlyViewedRepository : CrudRepository<RecentlyViewed, String> {

  @Query("select r from RecentlyViewed r where r.user.id= ?1")
  fun findByUser(id: String): RecentlyViewed

  @Query("select r.properties from RecentlyViewed r where r.user.id= ?1")
  fun findFirst10ByUser(id: String): MutableList<Property>
}
