package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface StateRepository : CrudRepository<State, String> {
  fun findFirstByNameAndCountry(name: String, country: Country): Optional<State>

  fun findFirstByGid(gid: Double): Optional<State>
  fun findFirstByName(name: String): Optional<State>

  @Query("select s from State s where lower(s.name) like %:name%")
  fun getAllPaginated(name: String, pageRequest: Pageable): Page<State>
}
