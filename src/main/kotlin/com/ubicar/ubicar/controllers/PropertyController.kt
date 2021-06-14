package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        return propertyFactory.convert(propertyService.save(createPropertyFactory.convert(propertyDTO)))
    }

    @GetMapping("/property/{id}")
    fun getProperty(@PathVariable id: String) : PropertyDTO {
        return propertyFactory.convert(propertyService.findById(id))
    }

    @PutMapping("/property/{id}")
    fun editProperty(@PathVariable id: String, @RequestBody propertyDTO: CreatePropertyDTO) : PropertyDTO {
        return propertyFactory.convert(propertyService.update(id, createPropertyFactory.convert(propertyDTO)))
    }
}
