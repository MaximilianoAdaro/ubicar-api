package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.City
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : CrudRepository<City, Long> {

    @Query(value = "select a from City a")
    override fun findAll() : List<City>
}
