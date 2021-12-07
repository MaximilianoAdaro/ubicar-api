package com.ubicar.ubicar.repositories.filter

import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.entities.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PropertyFilterRepository : CrudRepository<Filter, String> {

  @Query("select f from Filter f where f.user.id= ?1")
  fun findByUser(id: String): List<Filter>
}
