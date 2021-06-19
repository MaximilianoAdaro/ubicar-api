package com.ubicar.ubicar.controllers.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PropertyUserController(
    private val propertyService: PropertyService,
    private val propertyFactory: PropertyFactory
) {

    @PutMapping("/like/{id}")
    fun likeProperty(@PathVariable id: String) : PropertyDTO {
        return propertyFactory.convert(propertyService.like(id))
    }

    @PutMapping("/dislike/{id}")
    fun dislikeProperty(@PathVariable id: String) : PropertyDTO {
        return propertyFactory.convert(propertyService.dislike(id))
    }
}
