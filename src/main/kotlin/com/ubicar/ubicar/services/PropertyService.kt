package com.ubicar.ubicar.services

import com.ubicar.ubicar.entities.Entities
import com.ubicar.ubicar.repositories.PropertyRepository
import org.springframework.stereotype.Service

@Service
class PropertyService(private val repository: PropertyRepository) {

    fun findAll() : List<Entities.Property> {
        return repository.findAll()
    }

    fun save(property: Entities.Property) : Entities.Property {
        return repository.save(property)
    }

    fun findById(id: Long) : Entities.Property {
        return repository.findById(id).orElseThrow()
    }

    fun delete(property: Long) {
        repository.delete(findById(property))
    }

    class NotFoundException : RuntimeException("This entity does not exist")

}
