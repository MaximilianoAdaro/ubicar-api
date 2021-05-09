package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.repositories.PropertyRepository
import com.ubicar.ubicar.services.address.AddressService
import com.ubicar.ubicar.services.contact.ContactService
import com.ubicar.ubicar.services.openHouseDate.OpenHouseDateService
import org.springframework.stereotype.Service

@Service
class PropertyServiceImpl(private val propertyRepository: PropertyRepository,
                          private val addressService: AddressService,
                          private val contactService: ContactService,
                          private val openHouseDateService: OpenHouseDateService): PropertyService {

    override fun findAll() : List<Property> {
        return propertyRepository.findAll()
    }

    override fun save(property: Property) : Property {
        addressService.save(property.getAddress())
        property.getContacts().map { contactService.save(it) }
        property.getOpenHouse().map { openHouseDateService.save(it) }
        return propertyRepository.save(property)
    }

    override fun findById(id: Long) : Property {
        return propertyRepository.findById(id).orElseThrow()
    }

    override fun delete(property: Long) = propertyRepository.delete(findById(property))
}
