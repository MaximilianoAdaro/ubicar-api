package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class PropertyFactory: AbstractFactory<Property, PropertyDTO> {

    override fun convert(input: Property): PropertyDTO {
        return PropertyDTO(
            input.id,
            input.getTitle(),
            input.getPrice(),
            input.getCondition(),
            input.getType(),
            input.getAddress(),
            input.getSquareFoot(),
            input.getCoveredSquareFoot(),
            input.getLevels(),
            input.getConstructionDate(),
            input.getStyle(),
            input.getEnvironments(),
            input.getRooms(),
            input.getToilets(),
            input.getFullBaths(),
            input.getExpenses(),
            input.getAmenities(),
            input.getMaterials(),
            input.getSecurity(),
            input.getParkDescription(),
            input.getLinks(),
            input.getContacts(),
            input.getOpenHouse(),
            input.getComments()
        )
    }
}
