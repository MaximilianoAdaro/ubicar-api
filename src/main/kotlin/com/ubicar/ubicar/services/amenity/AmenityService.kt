package com.ubicar.ubicar.services.amenity

import com.ubicar.ubicar.entities.Amenity

interface AmenityService {

    fun save(amenity: Amenity) : Amenity

    fun findById(id: Long) : Amenity

    fun delete(amenity: Long)
}
