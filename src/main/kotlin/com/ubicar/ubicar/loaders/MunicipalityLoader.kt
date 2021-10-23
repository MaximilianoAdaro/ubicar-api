package com.ubicar.ubicar.loaders

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ubicar.ubicar.entities.Municipality
import com.ubicar.ubicar.loaders.loadersDto.AbstractMethod
import com.ubicar.ubicar.loaders.loadersDto.MunicipalityJson
import com.ubicar.ubicar.repositories.location.MunicipalityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class MunicipalityLoader(
  private val municipalityRepository: MunicipalityRepository,
  private val stateRepository: StateRepository
) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    if (municipalityRepository.totalAmount() > 1) return
    val municipalities: List<Municipality> = getAllMunicipalitiesFromFile()
    municipalities.forEach { municipality ->
      municipalityRepository.findFirstByGid(municipality.gid)
        .orElseGet { municipalityRepository.save(municipality) }
    }
  }

  private fun getAllMunicipalitiesFromFile(): List<Municipality> {
    val mapper = jacksonObjectMapper()
    mapper.registerKotlinModule()
    mapper.registerModule(JavaTimeModule())

    val content = AbstractMethod.getContentFromPath("/geoRef/municipalities.json")
    val jsonTextList: MunicipalityJson = mapper.readValue(content)
    return jsonTextList.municipios.map { municipalityDto ->
      val state = stateRepository.findFirstByGid(municipalityDto.provincia.id)
        .orElseThrow { NotFoundException("State not found") }
      municipalityDto.toMunicipality(state)
    }
  }

  override fun getOrder(): Int {
    return 12
  }
}
