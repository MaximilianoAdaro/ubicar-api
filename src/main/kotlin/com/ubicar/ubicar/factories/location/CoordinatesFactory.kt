package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.CoordinatesDTO
import com.ubicar.ubicar.entities.Coordinates
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class CoordinatesFactory : AbstractFactory<CoordinatesDTO, Coordinates> {

  override fun convert(input: CoordinatesDTO): Coordinates {
    return Coordinates(input.lat, input.long)
  }
}
