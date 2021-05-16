package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.CreatePropertyDTO
import com.ubicar.ubicar.entities.*
import com.ubicar.ubicar.repositories.AmenityRepository
import com.ubicar.ubicar.repositories.MaterialRepository
import com.ubicar.ubicar.repositories.SecurityRepository
import com.ubicar.ubicar.repositories.StyleRepository

class CreatePropertyFactory(private val styleRepository: StyleRepository,
                            private val amenityRepository: AmenityRepository,
                            private val materialRepository: MaterialRepository,
                            private val securityRepository: SecurityRepository) {

    fun convert(input: CreatePropertyDTO): Property {
        val amenities: MutableList<Amenity> = mutableListOf()
        input.amenities.map { amenities.add(amenityRepository.findById(it).get()) }

        val materials: MutableList<ConstructionMaterial> = mutableListOf()
        input.materials.map { materials.add(materialRepository.findById(it).get()) }

        val securities: MutableList<SecurityMeasure> = mutableListOf()
        input.security.map { securities.add(securityRepository.findById(it).get()) }

        return Property(
            0,
            input.title,
            input.price,
            Condition.valueOf(input.condition),
            TypeOfProperty.valueOf(input.type),
            input.address,
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
            input.contacts,
            input.openHouse,
            input.comments
        )
    }
}
