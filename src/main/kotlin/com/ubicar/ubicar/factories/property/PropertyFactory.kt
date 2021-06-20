package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class PropertyFactory : AbstractFactory<Property, PropertyDTO> {

  override fun convert(input: Property): PropertyDTO {
    return PropertyDTO(
      input.id,
      input.title,
      input.price,
      input.condition,
      input.type,
      input.address,
      input.squareFoot,
      input.coveredSquareFoot,
      input.levels,
      input.constructionDate,
      input.style,
      input.environments,
      input.rooms,
      input.toilets,
      input.fullBaths,
      input.expenses,
      input.amenities,
      input.materials,
      input.security,
      input.parkDescription,
      input.links,
      input.contacts,
      input.openHouse,
      input.comments
    )
  }
}
