package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property

interface PropertyService {

    fun findAll() : List<Property>

    fun save(property: Property) : Property

    fun findById(id: Long) : Property

    fun delete(property: Long)
}
