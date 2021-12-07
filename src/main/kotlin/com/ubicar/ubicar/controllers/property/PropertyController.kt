package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.factories.image.ImageFactory
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import com.ubicar.ubicar.services.predictor.PredictorService
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
  private val predictorService: PredictorService,
  private val imageFactory: ImageFactory
) {

  @PostMapping("/create")
  fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
    val property = createPropertyFactory.convert(propertyDTO)
    val savedProperty = if (property.id.isBlank())
      propertyService.save(property, listOf())
    else
      propertyService.update(property.id, property, listOf(), listOf())
    return propertyFactory.convert(savedProperty)
  }

  @PostMapping("/get-property-dto")
  fun getPropertyDto(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
    val property = createPropertyFactory.convert(propertyDTO)
    return propertyFactory.convert(property)
  }

  @PostMapping(
    "/create-with-images",
    consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE]
  )
  fun createPropertyWithImages(
    @RequestPart("property") propertyDTO: CreatePropertyDTO,
    @RequestPart("images", required = false) files: List<MultipartFile>?
  ): PropertyDTO {
    val images = files?.map { imageFactory.convert(it) }.orEmpty()
    val property = createPropertyFactory.convert(propertyDTO)
    val savedProperty = propertyService.save(property, images)
    val result = predictorService.requestPrediction(savedProperty)
    if (result.toDouble() > savedProperty.price) propertyService.isOpportunity(savedProperty.id)
    return propertyFactory.convert(savedProperty)
  }

  @PutMapping("/{id}")
  fun editProperty(@PathVariable id: String, @RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
    return propertyFactory.convert(
      propertyService.update(
        id,
        createPropertyFactory.convert(propertyDTO),
        listOf(),
        listOf()
      )
    )
  }

  @PutMapping("/with-images/{id}")
  fun editPropertyWithImages(
    @PathVariable id: String,
    @RequestPart("property") propertyDTO: CreatePropertyDTO,
    @RequestPart("images", required = false) files: List<MultipartFile>?,
    @RequestPart("imagesToDelete", required = false) imagesToDelete: List<String>?
  ): PropertyDTO {
    val property = createPropertyFactory.convert(propertyDTO)
    val images = files?.map { imageFactory.convert(it) }.orEmpty()
    val updatedProperty = propertyService.update(id, property, images, imagesToDelete.orEmpty())
    return propertyFactory.convert(updatedProperty)
  }

  @PutMapping("/opportunity/{id}")
  fun isOpportunity(@PathVariable id: String): PropertyDTO {
    return propertyFactory.convert(propertyService.isOpportunity(id))
  }
}
