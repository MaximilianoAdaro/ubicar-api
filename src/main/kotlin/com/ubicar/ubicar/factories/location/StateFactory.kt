package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.StateDTO
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.factories.AbstractFactory

class StateFactory: AbstractFactory<State, StateDTO> {

    override fun convert(input: State): StateDTO {
        return StateDTO(input.id, input.name)
    }
}
