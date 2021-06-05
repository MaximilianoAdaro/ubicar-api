package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Amenity
import org.springframework.data.repository.CrudRepository

interface AmenityRepository: CrudRepository<Amenity, String> {}
