package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.AddressDTO
import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.stereotype.Component

@Component
class AddressFactory(
  private val cityRepository: CityRepository,
  private val coordinatesFactory: CoordinatesFactory
) : AbstractFactory<AddressDTO, Address> {

  override fun convert(input: AddressDTO): Address {
    val city = cityRepository.findById(input.cityId)
      .orElseThrow { NotFoundException("City not Found") }
    val coordinates = coordinatesFactory.convert(input.coordinates)
    return Address(city, input.street, input.number, coordinates)
  }

  fun from(input: Address): AddressDTO {
    val city = input.city
    val state = city.state
    val country = state.country
    return AddressDTO(
      country.name,
      state.name,
      city.name,
      input.street,
      input.number,
      coordinatesFactory.from(input.coordinates)
    )
  }
}
