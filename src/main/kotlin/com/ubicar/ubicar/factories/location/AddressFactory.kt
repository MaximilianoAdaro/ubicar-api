package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.AddressDTO
import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import org.springframework.stereotype.Component

@Component
class AddressFactory(
    private val cityRepository: CityRepository,
    private val coordinatesFactory: CoordinatesFactory
) : AbstractFactory<AddressDTO, Address> {

    override fun convert(input: AddressDTO): Address {
        val city = cityRepository.findByName(input.city).orElseGet { City(input.city) }
        val coordinates = coordinatesFactory.convert(input.coordinates)
        return Address(input.name, city, input.street, input.number, coordinates)
    }

}
