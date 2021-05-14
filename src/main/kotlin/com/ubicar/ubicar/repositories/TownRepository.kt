package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Town
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TownRepository : CrudRepository<Town, Long> {

    @Query(value = "select a from Town")
    override fun findAll() : List<Town>
}
