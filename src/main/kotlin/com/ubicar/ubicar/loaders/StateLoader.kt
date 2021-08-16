package com.ubicar.ubicar.loaders

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.loaders.loadersDto.AbstractMethod
import com.ubicar.ubicar.loaders.loadersDto.StateJson
import com.ubicar.ubicar.repositories.location.CountryRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StateLoader(
  private val stateRepository: StateRepository,
  private val countryRepository: CountryRepository,
  private val abstractMethod: AbstractMethod
) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val country: Country = countryRepository.findFirstByName("Argentina").get()
    val states: List<State> = getAllStatesFromFile(country)
    states.forEach { state ->
      stateRepository.findFirstByGid(state.gid)
        .orElseGet { stateRepository.save(state) }
    }
  }

  private fun getAllStatesFromFile(country: Country): List<State> {
    val mapper = jacksonObjectMapper()
    mapper.registerKotlinModule()
    mapper.registerModule(JavaTimeModule())

    val content = abstractMethod.getContentFromPath("/geoRef/states.json")
    val jsonTextList: StateJson = mapper.readValue(content)
    return jsonTextList.provincias.map { it.toState(country) }
  }

  override fun getOrder(): Int {
    return 5
  }
}
