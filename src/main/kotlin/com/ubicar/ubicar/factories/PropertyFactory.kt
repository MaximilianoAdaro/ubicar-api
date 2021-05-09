package com.ubicar.ubicar.factories

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Property

class PropertyFactory: AbstractFactory<Property, PropertyDTO> {

    override fun convert(input: Property): PropertyDTO {
        return PropertyDTO(
            input.getId(),
            input.getTitle(),
            input.getPrice(),
            input.getCondition(),
            input.getAddress(),
            input.getSquareFoot(),
            input.getConstructionDate(),
            input.getStyle(),
            input.getRooms(),
            input.getQuarterBaths(),
            input.getHalfBaths(),
            input.getThreeQuarterBaths(),
            input.getFullBaths(),
            input.getExpenses()
        )
    }
}
