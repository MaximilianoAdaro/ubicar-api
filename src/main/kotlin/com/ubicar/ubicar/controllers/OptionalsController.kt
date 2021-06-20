package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.AmenityDTO
import com.ubicar.ubicar.dtos.MaterialDTO
import com.ubicar.ubicar.dtos.SecurityDTO
import com.ubicar.ubicar.dtos.StyleDTO
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.factories.optionals.AmenityFactory
import com.ubicar.ubicar.factories.optionals.MaterialFactory
import com.ubicar.ubicar.factories.optionals.SecurityFactory
import com.ubicar.ubicar.factories.optionals.StyleFactory
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
import com.ubicar.ubicar.repositories.property.SecurityRepository
import com.ubicar.ubicar.repositories.property.StyleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OptionalsController(
  private val amenityRepository: AmenityRepository,
  private val materialRepository: MaterialRepository,
  private val securityRepository: SecurityRepository,
  private val styleRepository: StyleRepository,
  private val amenityFactory: AmenityFactory,
  private val materialFactory: MaterialFactory,
  private val securityFactory: SecurityFactory,
  private val styleFactory: StyleFactory
) {

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

  @GetMapping("/styles")
  fun getStyles(): List<StyleDTO> {
    return styleRepository.findAll().map { styleFactory.convert(it) }
  }

  @GetMapping("/types")
  fun getTypes(): List<TypeOfProperty> {
    return TypeOfProperty.values().toList()
  }
}
