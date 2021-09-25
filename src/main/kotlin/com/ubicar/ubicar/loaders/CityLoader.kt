package com.ubicar.ubicar.loaders

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.loaders.loadersDto.AbstractMethod
import com.ubicar.ubicar.loaders.loadersDto.CityJson
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class CityLoader(
  private val cityRepository: CityRepository,
  private val stateRepository: StateRepository
) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    if (cityRepository.totalAmount() > 1) return
    val cities: List<City> = getAllCitiesFromFile()
    cities.forEach { city ->
      cityRepository.findFirstByGid(city.gid)
        .orElseGet { cityRepository.save(city) }
    }
  }

  private fun getAllCitiesFromFile(): List<City> {
    val mapper = jacksonObjectMapper()
    mapper.registerKotlinModule()
    mapper.registerModule(JavaTimeModule())

    val content = AbstractMethod.getContentFromPath("/geoRef/cities.json")
    val jsonTextList: CityJson = mapper.readValue(content)
    return jsonTextList.localidades.map { cityDto ->
      val state = stateRepository.findFirstByGid(cityDto.provincia.id)
        .orElseThrow { NotFoundException("State not found") }
      cityDto.toCity(state)
    }
  }

  override fun getOrder(): Int {
    return 6
  }
}
