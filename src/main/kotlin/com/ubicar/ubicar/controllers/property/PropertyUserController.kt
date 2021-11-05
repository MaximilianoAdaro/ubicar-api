package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.entities.Tags
import com.ubicar.ubicar.factories.filter.FilterFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.TagFactory
import com.ubicar.ubicar.services.filter.FilterService
import com.ubicar.ubicar.services.property.PropertyService
import com.ubicar.ubicar.services.property.TagsLikedService
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.NotFoundException
import com.ubicar.ubicar.utils.SessionUtils
import org.springframework.web.bind.annotation.*

@RestController
class PropertyUserController(
  private val propertyService: PropertyService,
  private val propertyFactory: PropertyFactory,
  private val sessionUtils: SessionUtils,
  private val filterService: FilterService,
  private val filterFactory: FilterFactory,
  private val userService: UserService,
  private val tagFactory: TagFactory,
  private val tagsLikedService: TagsLikedService
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

  @GetMapping("/public/selected-tags/{id}")
  fun getSelectedTags(@PathVariable id: String): List<String> {
    return try {
      val tagsLiked = tagsLikedService.findByPropertyIdAndUserId(id, userService.findLogged().id)
      tagsLiked?.tags?.map(tagFactory::convert) ?: listOf()
    } catch (e: NotFoundException) {
      listOf()
    }
  }

  @PutMapping("/set-tags/{id}")
  fun setTags(@PathVariable id: String, @RequestBody tags: List<String>): List<String> {
    val list = tags.map(tagFactory::deconvert)
    return tagsLikedService.update(id, userService.findLogged().id, list).tags.map(tagFactory::convert)
  }
}
