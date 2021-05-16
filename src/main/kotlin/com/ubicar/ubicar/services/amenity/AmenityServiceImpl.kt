package com.ubicar.ubicar.services.amenity

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.repositories.AmenitiesRepository
import org.springframework.stereotype.Service

@Service
class AmenityServiceImpl(private val amenitiesRepository: AmenitiesRepository): AmenityService {

    override fun save(amenity: Amenity): Amenity {
        return amenitiesRepository.save(amenity)
    }

    override fun findById(id: Long): Amenity {
        return amenitiesRepository.findById(id).orElseThrow()
    }

    override fun delete(amenity: Long) {
        amenitiesRepository.delete(findById(amenity))
    }
}
