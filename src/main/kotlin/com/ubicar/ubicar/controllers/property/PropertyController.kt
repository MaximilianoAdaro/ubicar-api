package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.factories.image.ImageFactory
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/property")
class PropertyController(
  private val propertyService: PropertyService,
  private val propertyFactory: PropertyFactory,
  private val createPropertyFactory: CreatePropertyFactory,
  val imageFactory: ImageFactory
) {

  @PostMapping("/create", consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
  fun createProperty(
    @RequestPart("property") propertyDTO: CreatePropertyDTO,
    @RequestPart("images", required = false) files: List<MultipartFile>?
  ): PropertyDTO {
    val images = files?.map { imageFactory.convert(it) } ?: listOf()
    return propertyFactory.convert(propertyService.save(createPropertyFactory.convert(propertyDTO), images))
  }

  @PutMapping("/{id}")
  fun editProperty(@PathVariable id: String, @RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
    return propertyFactory.convert(propertyService.update(id, createPropertyFactory.convert(propertyDTO)))
  }
}
