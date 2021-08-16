package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.State
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CityRepository : CrudRepository<City, String> {

  @Query("select c from City c where c.state.id = :#{#id}")
  fun findAllByStateId(@Param("id") id: String): List<City>

  fun findByNameAndState(name: String, state: State): Optional<City>

  fun findFirstByGid(gid: Double): Optional<City>
  fun findFirstByName(name: String): Optional<City>

  @Query("select c from City c join c.state s where lower(c.name) like %:name% and s.id = :stateId")
  fun getAllPaginated(name: String, stateId: String, pageRequest: Pageable): Page<City>
}
