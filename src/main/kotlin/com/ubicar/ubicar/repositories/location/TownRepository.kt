package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Town
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TownRepository: CrudRepository<Town, String> {

    @Query(value = "select t from Town t where t.city.id = :#{#id}")
    fun findAllByCityId(@Param("id") id: String): List<Town>
}
