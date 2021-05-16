package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory

class PropertyPreviewFactory: AbstractFactory<Property, PropertyPreviewDTO> {

    override fun convert(input: Property): PropertyPreviewDTO {
        return PropertyPreviewDTO(
            input.getId(),
            input.getTitle(),
            input.getPrice(),
            input.getCondition(),
            input.getType(),
            input.getAddress(),
            input.getSquareFoot(),
            input.getCoveredSquareFoot(),
            input.getRooms(),
            input.getToilettes(),
            input.getFullBaths()
        )
    }
}
