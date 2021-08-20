package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.UserContactDto
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTO
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PropertyService {

  fun findAll(pageable: Pageable): Page<Property>

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTO): List<Property>

  fun save(property: Property): Property

  fun findById(id: String): Property

  fun update(id: String, property: Property): Property

  fun delete(id: String)

  fun like(id: String): Property

  fun dislike(id: String): Property

  fun getAllByFilterPageable(filter: PropertyFilterDto, params: PropertyLazyTableDto): Page<Property>
  fun getAllFavoritePropertiesByUser(user: User): List<Property>
  fun getAllPropertiesOfUser(user: User): List<Property>
  fun contactOwner(id: String, contactDto: UserContactDto)
}
