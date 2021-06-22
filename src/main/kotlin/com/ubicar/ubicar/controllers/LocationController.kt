package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CityDTO
import com.ubicar.ubicar.dtos.StateDTO
import com.ubicar.ubicar.factories.location.CityFactory
import com.ubicar.ubicar.factories.location.StateFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController(
  private val cityRepository: CityRepository,
  private val stateRepository: StateRepository,
  private val stateFactory: StateFactory,
  private val cityFactory: CityFactory,
) {

  @GetMapping("/states")
  fun getStates(): List<StateDTO> {
    return stateRepository.findAll().map { stateFactory.convert(it) }
  }

  @GetMapping("/cities/{stateId}")
  fun getCities(@PathVariable stateId: String): List<CityDTO> {
    return cityRepository.findAllByStateId(stateId).map { cityFactory.convert(it) }
  }
}
