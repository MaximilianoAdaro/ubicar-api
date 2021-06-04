package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PropertyService {

    fun findAll(pageable: Pageable) : Page<Property>

    fun save(property: Property) : Property

    fun findById(id: Long) : Property

    fun update(id: Long, property: Property): Property

    fun delete(property: Long)

    fun like(id: Long): Property

    fun dislike(id: Long): Property
}
