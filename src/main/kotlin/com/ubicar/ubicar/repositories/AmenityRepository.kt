package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Amenity
import org.springframework.data.repository.CrudRepository

interface AmenityRepository: CrudRepository<Amenity, Long> {}
