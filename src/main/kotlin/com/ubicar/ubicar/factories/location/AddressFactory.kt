package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.AddressDTO
import com.ubicar.ubicar.dtos.CoordinatesDTO
import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.stereotype.Component

@Component
class AddressFactory(
  private val cityRepository: CityRepository
) : AbstractFactory<AddressDTO, Address> {

  override fun convert(input: AddressDTO): Address {
    val city = cityRepository.findById(input.cityId!!)
      .orElseThrow { NotFoundException("City not Found") }
    return Address(city, input.street, input.number, input.coordinates.toPoint())
  }

  fun from(input: Address): AddressDTO {
    val city = input.city
    val state = city.state
    return AddressDTO(
      state.id,
      state.name,
      city.id,
      city.name,
      input.street,
      input.number,
      CoordinatesDTO.from(input.coordinates)
    )
  }
}
