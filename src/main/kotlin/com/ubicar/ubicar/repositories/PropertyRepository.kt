package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Property
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PropertyRepository : CrudRepository<Property, Long> {

//    @Query(value = "select p from Property p")
    fun findAll(pageable: Pageable) : Page<Property>
}
