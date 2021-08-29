package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.factories.filter.FilterFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.services.filter.FilterService
import com.ubicar.ubicar.services.property.PropertyService
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.SessionUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PropertyUserController(
  private val propertyService: PropertyService,
  private val propertyFactory: PropertyFactory,
  private val sessionUtils: SessionUtils,
  private val filterService: FilterService,
  private val filterFactory: FilterFactory,
  private val userService: UserService
) {

  @PutMapping("/like/{id}")
  fun likeProperty(@PathVariable id: String): PropertyDTO {
    return propertyFactory.convert(propertyService.like(id))
  }

  @PutMapping("/dislike/{id}")
  fun dislikeProperty(@PathVariable id: String): PropertyDTO {
    return propertyFactory.convert(propertyService.dislike(id))
  }

  @GetMapping("/property/favorites")
  fun getFavoriteProperties(): List<PropertyDTO> {
    val user = sessionUtils.getTokenUserInformation()
    return propertyService.getAllFavoritePropertiesByUser(user).map(propertyFactory::convert)
  }

  @GetMapping("/property/own")
  fun getMyProperties(): List<PropertyDTO> {
    val user = sessionUtils.getTokenUserInformation()
    return propertyService.getAllPropertiesOfUser(user).map(propertyFactory::convert)
  }

  @GetMapping("/get-filters")
  fun getMyFilters(): List<PropertyFilterDto> {
    return filterService.findByUser(userService.findLogged().id).map { filterFactory.convert(it) }
  }
}
