package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Town
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TownRepository: CrudRepository<Town, Long> {

//    fun findAllByCityId(@Param("id") id: Long): List<Town>

    fun findAllByCityId(id: Long): List<Town>
}
