package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.Town
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.TownRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class TownLoader(private val townRepository: TownRepository, private val cityRepository: CityRepository) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val city: City = cityRepository.findByName("CABA").orElseThrow { NotFoundException("City not found") }
    val towns: MutableList<Town> = mutableListOf()
    towns.add(Town("Belgrano", city))
    towns.add(Town("Caballito", city))
    towns.add(Town("Colegiales", city))
    towns.add(Town("Recoleta", city))
    towns.add(Town("Flores", city))
    towns.add(Town("Retiro", city))

    towns.map { townRepository.save(it) }
  }

  override fun getOrder(): Int {
    return 7
  }
}
