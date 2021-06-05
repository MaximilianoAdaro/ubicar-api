package com.ubicar.ubicar.services.amenity

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.repositories.property.AmenityRepository
import org.springframework.stereotype.Service

@Service
class AmenityServiceImpl(private val amenitiesRepository: AmenityRepository): AmenityService {

    override fun save(amenity: Amenity): Amenity {
        return amenitiesRepository.save(amenity)
    }

    override fun findById(id: String): Amenity {
        return amenitiesRepository.findById(id).orElseThrow()
    }

    override fun delete(id: String) {
        amenitiesRepository.delete(findById(id))
    }
}
