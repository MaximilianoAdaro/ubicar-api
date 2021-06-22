package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.factories.location.AddressFactory
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
import com.ubicar.ubicar.repositories.property.SecurityRepository
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class CreatePropertyFactory(
  private val styleRepository: StyleRepository,
  private val amenityRepository: AmenityRepository,
  private val materialRepository: MaterialRepository,
  private val securityRepository: SecurityRepository,
  private val contactFactory: ContactFactory,
  private val openHouseDateFactory: OpenHouseDateFactory,
  private val addressFactory: AddressFactory
) {

  fun convert(input: CreatePropertyDTO): Property {
    val amenities: MutableList<Amenity> = mutableListOf()
    input.amenities.map { amenities.add(amenityRepository.findById(it).orElseThrow { NotFoundException("Amenity not found") }) }

    val materials: MutableList<ConstructionMaterial> = mutableListOf()
    input.materials.map { materials.add(materialRepository.findById(it).orElseThrow { NotFoundException("Material not found") }) }

    val securities: MutableList<SecurityMeasure> = mutableListOf()
    input.security.map { securities.add(securityRepository.findById(it).orElseThrow { NotFoundException("Security not found") }) }

    val address = addressFactory.convert(input.address)

    val contacts = input.contacts.map(contactFactory::from).toMutableList()
    val openHouse = input.openHouse.map(openHouseDateFactory::from).toMutableList()

    val style = styleRepository.findById(input.style).orElseThrow { NotFoundException("Style not found") }

    return Property(
      input.title,
      input.price,
      Condition.valueOf(input.condition),
      TypeOfProperty.valueOf(input.type),
      address,
      input.squareFoot,
      input.coveredSquareFoot,
      input.levels,
      input.constructionDate,
      style,
      input.environments,
      input.rooms,
      input.toilets,
      input.fullBaths,
      input.expenses,
      amenities,
      materials,
      securities,
      input.parkDescription,
      input.links,
      contacts,
      openHouse,
      input.comments,
      LocalDate.now()
    )
  }
}
