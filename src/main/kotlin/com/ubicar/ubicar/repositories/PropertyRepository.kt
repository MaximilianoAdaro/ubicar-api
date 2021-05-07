package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Entities
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PropertyRepository : CrudRepository<Entities.Property, Long> {

    @Query(value = "select * from property", nativeQuery = true)
    override fun findAll() : List<Entities.Property>
}
