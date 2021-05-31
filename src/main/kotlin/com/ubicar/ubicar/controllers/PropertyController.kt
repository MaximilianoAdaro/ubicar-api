package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.repositories.location.TownRepository
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
import com.ubicar.ubicar.repositories.property.SecurityRepository
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
class PropertyController(private val propertyService: PropertyService,
                         amenityRepository: AmenityRepository,
                         styleRepository: StyleRepository,
                         materialRepository: MaterialRepository,
                         securityRepository: SecurityRepository,
                         townRepository: TownRepository
) {

    private val propertyFactory: PropertyFactory = PropertyFactory()
    private val propertyPreviewFactory: PropertyPreviewFactory = PropertyPreviewFactory()
    private val createPropertyFactory: CreatePropertyFactory = CreatePropertyFactory(styleRepository, amenityRepository, materialRepository, securityRepository, townRepository)

    @GetMapping("/preview")
    fun getProperties(@RequestParam page: Int): Page<PropertyPreviewDTO> {
        return propertyService.findAll(PageRequest.of(page, 16)).map { propertyPreviewFactory.convert(it) }
    }

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        return propertyFactory.convert(propertyService.save(createPropertyFactory.convert(propertyDTO)))
    }

    @PutMapping("/property/{id}")
    fun editProperty(@PathVariable id: Long, @RequestBody propertyDTO: CreatePropertyDTO) : PropertyDTO {
        return propertyFactory.convert(propertyService.update(id, createPropertyFactory.convert(propertyDTO)))
    }
}
