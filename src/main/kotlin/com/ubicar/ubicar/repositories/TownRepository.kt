package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Town
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TownRepository: CrudRepository<Town, Long> {

    @Query(value = "select t from Town t where t.city.id = :#{#id}")
    fun findAllByCityId(@Param("id") id: Long): List<Town>
}
