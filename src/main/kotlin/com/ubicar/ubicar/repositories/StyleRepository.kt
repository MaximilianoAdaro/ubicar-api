package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Style
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface StyleRepository : CrudRepository<Style, Long> {

    @Query(value = "select s from Style s")
    override fun findAll() : List<Style>
}
