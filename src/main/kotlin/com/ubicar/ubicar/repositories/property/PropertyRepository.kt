package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Property
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PropertyRepository : CrudRepository<Property, String> {

  fun findAll(pageable: Pageable): Page<Property>

  @Query(value = "select p from Property p where p.owner.id = :#{#id}")
  fun findByOwnerId(id: String): List<Property>
}
