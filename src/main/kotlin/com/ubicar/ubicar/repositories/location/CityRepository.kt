package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.City
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface CityRepository: CrudRepository<City, Long> {

    @Query(value = "select c from City c where c.state.id = :#{#id}")
    fun findAllByStateId(@Param("id") id: Long): List<City>

    fun findByName(name: String): Optional<City>
}
