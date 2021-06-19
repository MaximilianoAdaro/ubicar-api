package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.factories.property.CreatePropertyFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/property")
class PropertyController(
    private val propertyService: PropertyService,
    private val propertyFactory: PropertyFactory,
    private val createPropertyFactory: CreatePropertyFactory
) {

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        return propertyFactory.convert(propertyService.save(createPropertyFactory.convert(propertyDTO)))
    }

    @PutMapping("/{id}")
    fun editProperty(@PathVariable id: String, @RequestBody propertyDTO: CreatePropertyDTO) : PropertyDTO {
        return propertyFactory.convert(propertyService.update(id, createPropertyFactory.convert(propertyDTO)))
    }
}
