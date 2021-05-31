package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory

class PropertyPreviewFactory: AbstractFactory<Property, PropertyPreviewDTO> {

    override fun convert(input: Property): PropertyPreviewDTO {
        return PropertyPreviewDTO(
            input.id,
            input.title,
            input.price,
            input.condition,
            input.type,
            input.address,
            input.squareFoot,
            input.coveredSquareFoot,
            input.rooms,
            input.toilets,
            input.fullBaths
        )
    }
}
