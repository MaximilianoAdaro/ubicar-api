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
            input.getType(),
            input.getAddress(),
            input.getSquareFoot(),
            input.getCoveredSquareFoot(),
            input.getLevels(),
            input.getConstructionDate(),
            input.getStyle(),
            input.getRooms(),
            input.getQuarterBaths(),
            input.getHalfBaths(),
            input.getThreeQuarterBaths(),
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