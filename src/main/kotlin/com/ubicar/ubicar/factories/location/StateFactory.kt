package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.CoordinatesDTO
import com.ubicar.ubicar.dtos.StateDTO
import com.ubicar.ubicar.dtos.StateWithCentroidDTO
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class StateFactory : AbstractFactory<State, StateDTO> {

  override fun convert(input: State): StateDTO {
    return StateDTO(input.id, input.name)
  }
}

@Component
class StateWithCentroidFactory : AbstractFactory<State, StateWithCentroidDTO> {

  override fun convert(input: State): StateWithCentroidDTO {
    return StateWithCentroidDTO(input.id, input.name, CoordinatesDTO.from(input.centroid))
  }
}


