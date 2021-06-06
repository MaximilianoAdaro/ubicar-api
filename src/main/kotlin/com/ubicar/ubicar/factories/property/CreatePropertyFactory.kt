package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.entities.*
import com.ubicar.ubicar.repositories.location.TownRepository
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
import com.ubicar.ubicar.repositories.property.SecurityRepository
import com.ubicar.ubicar.repositories.property.StyleRepository
import org.springframework.stereotype.Component

@Component
class CreatePropertyFactory(private val styleRepository: StyleRepository,
                            private val amenityRepository: AmenityRepository,
                            private val materialRepository: MaterialRepository,
                            private val securityRepository: SecurityRepository,
                            private val townRepository: TownRepository,
                            private val contactFactory: ContactFactory,
                            private val openHouseDateFactory: OpenHouseDateFactory
) {

    fun convert(input: CreatePropertyDTO): Property {
        val amenities: MutableList<Amenity> = mutableListOf()
        input.amenities.map { amenities.add(amenityRepository.findById(it).get()) }

        val materials: MutableList<ConstructionMaterial> = mutableListOf()
        input.materials.map { materials.add(materialRepository.findById(it).get()) }

        val securities: MutableList<SecurityMeasure> = mutableListOf()
        input.security.map { securities.add(securityRepository.findById(it).get()) }

        val town: Town = townRepository.findById(input.address.town_id).get()
        val address = Address(town, input.address.postalCode, input.address.street, input.address.number, input.address.department)

        val contacts = input.contacts.map(contactFactory::from).toMutableList()
        val openHouse = input.openHouse.map(openHouseDateFactory::from).toMutableList()

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
            styleRepository.findById(input.style).get(),
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
            input.comments
        )
    }
}
