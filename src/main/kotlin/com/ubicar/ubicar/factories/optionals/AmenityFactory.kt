package com.ubicar.ubicar.factories.optionals

import com.ubicar.ubicar.dtos.AmenityDTO
import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.factories.AbstractFactory

class AmenityFactory: AbstractFactory<Amenity, AmenityDTO> {

    override fun convert(input: Amenity): AmenityDTO {
        return AmenityDTO(input.id, input.label)
    }
}
