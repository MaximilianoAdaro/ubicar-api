package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.CityDTO
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class CityFactory : AbstractFactory<City, CityDTO> {

  override fun convert(input: City): CityDTO {
    return CityDTO(input.id, input.name)
  }
}
