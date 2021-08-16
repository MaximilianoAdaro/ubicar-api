package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.repositories.location.CountryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class CountryLoader(private val countryRepository: CountryRepository) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val name = "Argentina"
    countryRepository.findFirstByName(name).orElseGet { countryRepository.save(Country(name)) }
  }

  override fun getOrder(): Int {
    return 4
  }
}
