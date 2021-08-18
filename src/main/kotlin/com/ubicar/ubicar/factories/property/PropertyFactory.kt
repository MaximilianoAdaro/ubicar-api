package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.factories.location.AddressFactory
import com.ubicar.ubicar.factories.optionals.MaterialFactory
import com.ubicar.ubicar.factories.optionals.SecurityFactory
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.stereotype.Component

@Component
class PropertyFactory(
  private val materialFactory: MaterialFactory,
  private val securityFactory: SecurityFactory,
  private val contactFactory: ContactFactory,
  private val openHouseDateFactory: OpenHouseDateFactory,
  private val addressFactory: AddressFactory,
  private val userService: UserService
) : AbstractFactory<Property, PropertyDTO> {

  override fun convert(input: Property): PropertyDTO {
    val materials = input.materials.map(materialFactory::convert).toMutableList()
    val security = input.security.map(securityFactory::convert).toMutableList()
    val contacts = input.contacts.map(contactFactory::convert).toMutableList()
    val openHouse = input.openHouse.map(openHouseDateFactory::convert).toMutableList()
    val images = input.images.map { it.id }

    var liked = false
    try {
      val user = userService.findLogged()
      for (prop in user.likedProperties) {
        if (prop.id == input.id) {
          liked = true
        }
      }
    } catch (e: NotFoundException) {
      liked = false
    }

    val address = addressFactory.from(input.address)

    return PropertyDTO(
      input.id,
      input.title,
      input.price,
      input.condition,
      input.type,
      address,
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
      materials,
      security,
      input.parkDescription,
      input.links,
      contacts,
      openHouse,
      input.comments,
      liked,
      images = images
    )
  }
}
