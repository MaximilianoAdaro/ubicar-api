package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Style
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface StyleRepository : CrudRepository<Style, Long> {

    @Query(value = "select * from style", nativeQuery = true)
    override fun findAll() : List<Style>
}
