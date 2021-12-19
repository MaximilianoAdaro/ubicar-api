package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Department
import com.ubicar.ubicar.entities.State
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface DepartmentRepository : CrudRepository<Department, String> {

  @Query("select d from Department d where d.state.id = :#{#id}")
  fun findAllByStateId(@Param("id") id: String): List<Department>

  fun findByNameAndState(name: String, state: State): Optional<Department>

  fun findFirstByGid(gid: Double): Optional<Department>
  fun findFirstByName(name: String): Optional<Department>

  @Query("select d from Department d join d.state s where lower(d.name) like %:name% and s.id = :stateId")
  fun getAllPaginated(name: String, stateId: String, pageRequest: Pageable): Page<Department>

  @Query("select d from Department d where lower(d.name) like %:name%")
  fun getAllPaginatedWithoutState(name: String, pageRequest: Pageable): Page<Department>

  @Query("select count(d) from Department d")
  fun totalAmount(): Double
}
