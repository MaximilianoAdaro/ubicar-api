package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PropertyService {

  fun findAll(pageable: Pageable): Page<Property>

  fun save(property: Property): Property

  fun findById(id: String): Property

  fun update(id: String, property: Property): Property

  fun delete(id: String)

  fun like(id: String): Property

  fun dislike(id: String): Property

  fun getAllByFilterPageable(filter: PropertyFilterDto, params: PropertyLazyTableDto): Page<Property>
}
