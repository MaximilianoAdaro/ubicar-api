package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.AddressDTO
import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.CountryRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import org.springframework.stereotype.Component

@Component
class AddressFactory(
  private val countryRepository: CountryRepository,
  private val stateRepository: StateRepository,
  private val cityRepository: CityRepository,
  private val coordinatesFactory: CoordinatesFactory
) : AbstractFactory<AddressDTO, Address> {

  override fun convert(input: AddressDTO): Address {
    val country: Country = countryRepository.findFirstByName(input.country)
      .orElseGet { countryRepository.save(Country(input.country)) }
    val state: State = stateRepository.findFirstByNameAndCountry(input.state, country)
      .orElseGet { stateRepository.save(State(input.state, country)) }
    val city = cityRepository.findByNameAndSate(input.city, state)
      .orElseGet { cityRepository.save(City(input.city, state)) }
    val coordinates = coordinatesFactory.convert(input.coordinates)
    return Address(city, input.street, input.number, coordinates)
  }
}
