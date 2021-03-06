package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.factories.location.AddressFactory
import org.springframework.stereotype.Component

@Component
class PropertyPreviewFactory(
  val addressFactory: AddressFactory
) : AbstractFactory<Property, PropertyPreviewDTO> {

  override fun convert(input: Property): PropertyPreviewDTO {
    val address = if(input.address != null) addressFactory.from(input.address!!)
    else null
    return PropertyPreviewDTO(
      input.id,
      input.title,
      input.price,
      input.condition,
      input.type,
      address,
      input.squareFoot,
      input.coveredSquareFoot,
      input.rooms,
      input.toilets,
      input.fullBaths
    )
  }
}
