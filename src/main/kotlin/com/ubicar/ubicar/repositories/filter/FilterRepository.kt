package com.ubicar.ubicar.repositories.filter

import com.ubicar.ubicar.entities.Filter
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FilterRepository : CrudRepository<Filter, String> {

  @Query(value = "select f from Filter f where f.user.id = :#{#id}")
  fun findByUser(id: String): List<Filter>
}
