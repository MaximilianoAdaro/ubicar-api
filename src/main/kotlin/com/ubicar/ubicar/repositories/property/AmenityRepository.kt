package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Amenity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AmenityRepository: CrudRepository<Amenity, String> {
    fun findFirstByLabel(label: String) : Optional<Amenity>
}
