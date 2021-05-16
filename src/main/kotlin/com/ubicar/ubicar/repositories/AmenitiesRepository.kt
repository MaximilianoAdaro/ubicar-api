package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Amenity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AmenitiesRepository : CrudRepository<Amenity, Long> {

//    @Query(value = "select a from Amenity")
//    override fun findAll() : List<Amenity>
}
