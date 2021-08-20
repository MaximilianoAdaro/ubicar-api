package com.ubicar.ubicar.controllers.publicEndpoints

import com.ubicar.ubicar.dtos.CityDTO
import com.ubicar.ubicar.dtos.StateDTO
import com.ubicar.ubicar.factories.location.CityFactory
import com.ubicar.ubicar.factories.location.StateFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/public")
class LocationController(
  private val cityRepository: CityRepository,
  private val stateRepository: StateRepository,
  private val stateFactory: StateFactory,
  private val cityFactory: CityFactory,
) {

  @GetMapping("/states")
  fun getStates(
    @RequestParam(value = "name", required = false) name: Optional<String>,
    @RequestParam(value = "size", required = false) size: Optional<Int>,
    @RequestParam(value = "page", required = false) page: Optional<Int>,
  ): Page<StateDTO> {
    val pageRequest: Pageable = PageRequest.of(page.orElse(0), size.orElse(10))
    val nameLowerCase = name.orElse("%").toLowerCase()
    return stateRepository.getAllPaginated(nameLowerCase, pageRequest).map { stateFactory.convert(it) }
  }

  @GetMapping("/cities/{stateId}")
  fun getCities(
    @PathVariable stateId: String,
    @RequestParam(value = "name", required = false) name: Optional<String>,
    @RequestParam(value = "size", required = false) size: Optional<Int>,
    @RequestParam(value = "page", required = false) page: Optional<Int>,
  ): Page<CityDTO> {
    val pageRequest: Pageable = PageRequest.of(page.orElse(0), size.orElse(10))
    val nameLowerCase = name.orElse("%").toLowerCase()
    return cityRepository.getAllPaginated(nameLowerCase, stateId, pageRequest).map { cityFactory.convert(it) }
  }


  @GetMapping("/cities-and-states")
  fun getCitiesAndStates(
    @RequestParam(value = "name", required = false) name: Optional<String>,
    @RequestParam(value = "size", required = false) size: Optional<Int>,
    @RequestParam(value = "page", required = false) page: Optional<Int>,
  ): Page<CityDTO> {
    val pageRequest: Pageable = PageRequest.of(page.orElse(0), size.orElse(10))
    val nameLowerCase = name.orElse("%").toLowerCase()
    return cityRepository.getAllPaginated(nameLowerCase, "", pageRequest).map { cityFactory.convert(it) }
  }


}
