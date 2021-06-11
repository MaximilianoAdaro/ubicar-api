package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.dtos.filter.PropertySort
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PropertyController(
    private val propertyService: PropertyService,
    private val propertyFactory: PropertyFactory,
    private val propertyPreviewFactory: PropertyPreviewFactory,
    private val createPropertyFactory: CreatePropertyFactory
) {

    @GetMapping("/preview")
    fun getProperties(@RequestParam page: Int): Page<PropertyPreviewDTO> {
        return propertyService.findAll(PageRequest.of(page, 16)).map { propertyPreviewFactory.convert(it) }
    }

    @GetMapping("/preview/by-filter")
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
        return propertyService.getAllByFilterPageable(filter, propertyLazyTableDto).map { propertyPreviewFactory.convert(it) }
    }

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        return propertyFactory.convert(propertyService.save(createPropertyFactory.convert(propertyDTO)))
    }

    @PutMapping("/property/{id}")
    fun editProperty(@PathVariable id: String, @RequestBody propertyDTO: CreatePropertyDTO) : PropertyDTO {
        return propertyFactory.convert(propertyService.update(id, createPropertyFactory.convert(propertyDTO)))
    }
}
