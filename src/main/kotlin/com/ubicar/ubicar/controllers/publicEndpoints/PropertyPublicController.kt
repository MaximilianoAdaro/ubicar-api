package com.ubicar.ubicar.controllers.publicEndpoints

import com.ubicar.ubicar.dtos.*
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.dtos.filter.PropertySort
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/public/property")
class PropertyPublicController(
  private val propertyService: PropertyService,
  private val propertyFactory: PropertyFactory,
  private val propertyPreviewFactory: PropertyPreviewFactory
) {

  @GetMapping("/preview")
  fun getProperties(@RequestParam page: Int): Page<PropertyPreviewDTO> {
    return propertyService.findAll(PageRequest.of(page, 16)).map { propertyPreviewFactory.convert(it) }
  }

  @PostMapping("/preview/by-filter")
  fun getPropertiesFiltered(
    @RequestBody filter: PropertyFilterDto,
    @RequestParam(value = "page", required = false) page: Optional<Int>,
    @RequestParam(value = "size", required = false) size: Optional<Int>,
    @RequestParam(value = "direction", required = false) direction: Optional<Sort.Direction>,
    @RequestParam(value = "property", required = false) property: Optional<PropertySort>,
  ): Page<PropertyPreviewDTO> {
    val propertyLazyTableDto = PropertyLazyTableDto(
      page.orElse(0),
      size.orElse(16),
      direction.orElse(Sort.Direction.ASC),
      property.orElse(PropertySort.CREATION_DATE)
    )
    return propertyService.getAllByFilterPageable(filter, propertyLazyTableDto)
      .map { propertyPreviewFactory.convert(it) }
  }

  @GetMapping("/{id}")
  fun getProperty(@PathVariable id: String): PropertyDTO {
    return propertyFactory.convert(propertyService.findById(id))
  }

  @GetMapping("/contact/{id}")
  fun contactPropertyOwner(@PathVariable id: String, @RequestBody contactDto: UserContactDto) {
    propertyService.contactOwner(id, contactDto)
  }
}
