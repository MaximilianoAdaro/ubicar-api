package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.factories.PropertyFactory
import com.ubicar.ubicar.factories.PropertyPreviewFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PropertyController(private val propertyService: PropertyService) {

    private val propertyFactory: PropertyFactory = PropertyFactory()
    private val propertyPreviewFactory = PropertyPreviewFactory()

    @GetMapping("/preview")
    fun getProperties(): List<PropertyPreviewDTO> {
        return propertyService.findAll().map { propertyPreviewFactory.convert(it) }
    }

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        return propertyFactory.convert(propertyService.save(propertyDTO.render()))
    }
}
