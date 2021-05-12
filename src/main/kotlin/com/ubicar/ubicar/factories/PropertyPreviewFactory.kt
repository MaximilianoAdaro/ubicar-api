package com.ubicar.ubicar.factories

import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.entities.Property

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
            input.getHalfBaths(),
            input.getFullBaths()
        )
    }
}
