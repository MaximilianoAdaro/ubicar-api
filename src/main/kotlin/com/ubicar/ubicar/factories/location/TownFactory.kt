package com.ubicar.ubicar.factories.location

import com.ubicar.ubicar.dtos.TownDTO
import com.ubicar.ubicar.entities.Town
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class TownFactory: AbstractFactory<Town, TownDTO> {

    override fun convert(input: Town): TownDTO {
        return TownDTO(input.id, input.name)
    }
}
