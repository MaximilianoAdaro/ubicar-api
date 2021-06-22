package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.State
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CityRepository : CrudRepository<City, String> {

  @Query(value = "select c from City c where c.state.id = :#{#id}")
  fun findAllByStateId(@Param("id") id: String): List<City>

  fun findByNameAndSate(name: String, state: State): Optional<City>
}
