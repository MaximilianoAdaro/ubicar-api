package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Country
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : CrudRepository<Country, Long> {

//    @Query(value = "select a from Country")
    override fun findAll() : List<Country>
}
