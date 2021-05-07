package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Entities
import com.ubicar.ubicar.services.PropertyService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PropertyController(private val service: PropertyService) {

    @GetMapping("/list")
    fun getProperties(): ResponseEntity<List<PropertyDTO.PropertyDTO>> {
        return ResponseEntity.ok(service.findAll().map { it.render() })
    }

    @PostMapping("/create")
    fun createProperty(@RequestBody propertyDTO: PropertyDTO.CreatePropertyDTO): ResponseEntity<PropertyDTO.PropertyDTO> {
        val property : Entities.Property = service.save(propertyDTO.render())
        return ResponseEntity.ok(property.render())
    }

    fun Entities.Property.render() = PropertyDTO.PropertyDTO(
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
        expenses)

    fun PropertyDTO.CreatePropertyDTO.render() = Entities.Property(
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
