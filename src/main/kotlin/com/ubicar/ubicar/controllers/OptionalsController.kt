package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.AmenityDTO
import com.ubicar.ubicar.dtos.MaterialDTO
import com.ubicar.ubicar.dtos.SecurityDTO
import com.ubicar.ubicar.factories.optionals.AmenityFactory
import com.ubicar.ubicar.factories.optionals.MaterialFactory
import com.ubicar.ubicar.factories.optionals.SecurityFactory
import com.ubicar.ubicar.repositories.AmenityRepository
import com.ubicar.ubicar.repositories.MaterialRepository
import com.ubicar.ubicar.repositories.SecurityRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OptionalsController(private val amenityRepository: AmenityRepository,
                          private val materialRepository: MaterialRepository,
                          private val securityRepository: SecurityRepository) {

    private val amenityFactory: AmenityFactory = AmenityFactory()
    private val materialFactory: MaterialFactory = MaterialFactory()
    private val securityFactory: SecurityFactory = SecurityFactory()

    @GetMapping("/amenities")
    fun getAmenities(): List<AmenityDTO> {
        return amenityRepository.findAll().map { amenityFactory.convert(it) }
    }

    @GetMapping("/materials")
    fun getMaterials(): List<MaterialDTO> {
        return materialRepository.findAll().map { materialFactory.convert(it) }
    }

    @GetMapping("/securities")
    fun getSecurities(): List<SecurityDTO> {
        return securityRepository.findAll().map { securityFactory.convert(it) }
    }
}
