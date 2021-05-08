package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Entities
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.services.PropertyService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PropertyController(private val propertyService: PropertyService) {

    @GetMapping("/list")
    fun getProperties(): List<PropertyDTO> {
        return propertyService.findAll().map { it.render() }
    }

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: CreatePropertyDTO): PropertyDTO {
        val property: Property = propertyService.save(propertyDTO.render())
        return property.render()
    }

    fun Property.render() = PropertyDTO(
        id,
        price,
        condition,
        address,
        squareFoot,
        constructionDate,
        style,
        rooms,
        quarterBaths,
        halfBaths,
        threeQuarterBaths,
        fullBaths,
        expenses
    )

    fun CreatePropertyDTO.render() = Property(
        0,
        price,
        condition,
        address,
        squareFoot,
        constructionDate,
        style,
        rooms,
        quarterBaths,
        halfBaths,
        threeQuarterBaths,
        fullBaths,
        expenses
    )
}
