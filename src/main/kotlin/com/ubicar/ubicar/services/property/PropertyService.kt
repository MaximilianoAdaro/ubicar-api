package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.UserContactDto
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTO
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Image
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.Tag
import com.ubicar.ubicar.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PropertyService {

  fun findAll(pageable: Pageable): Page<Property>

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat): List<String>

  fun findAllInViewBoxProp(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat): List<Property>

  fun findAllInViewBoxFiltered(filter: PropertyFilterDto, viewBoxCoordinatesDTOFloat: ViewBoxCoordinatesDTOFloat): List<String>

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTO): List<Property>

  fun save(property: Property, images: List<Image>): Property

  fun findById(id: String): Property

  fun update(id: String, property: Property, images: List<Image>, imagesToDelete: List<String>): Property

  fun delete(id: String)

  fun like(id: String): Property

  fun dislike(id: String): Property
  fun getAllByFilterPageable(filter: PropertyFilterDto, params: PropertyLazyTableDto, viewBoxCoordinatesDTOFloat: ViewBoxCoordinatesDTOFloat): Page<Property>
  fun getAllFavoritePropertiesByUser(user: User): List<Property>
  fun getAllPropertiesOfUser(user: User): List<Property>
  fun contactOwner(id: String, contactDto: UserContactDto)
  fun setTags(id: String, tags: MutableList<Tag>): Property
}
