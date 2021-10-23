package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Municipality
import com.ubicar.ubicar.entities.State
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface MunicipalityRepository : CrudRepository<Municipality, String> {

  @Query("select m from Municipality m where m.state.id = :#{#id}")
  fun findAllByStateId(@Param("id") id: String): List<Municipality>

  fun findByNameAndState(name: String, state: State): Optional<Municipality>

  fun findFirstByGid(gid: Double): Optional<Municipality>
  fun findFirstByName(name: String): Optional<Municipality>

  @Query("select m from Municipality m join m.state s where lower(m.name) like %:name% and s.id = :stateId")
  fun getAllPaginated(name: String, stateId: String, pageRequest: Pageable): Page<Municipality>

  @Query("select m from Municipality m where lower(m.name) like %:name%")
  fun getAllPaginatedWithoutState(name: String, pageRequest: Pageable): Page<Municipality>

  @Query("select count(m) from Municipality m")
  fun totalAmount(): Double
}
