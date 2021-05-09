package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.repositories.PropertyRepository
import com.ubicar.ubicar.services.address.AddressService
import org.springframework.stereotype.Service

@Service
class PropertyServiceImpl(private val repository: PropertyRepository,
                          private val addressService: AddressService): PropertyService {

    override fun findAll() : List<Property> {
        return repository.findAll()
    }

    override fun save(property: Property) : Property {
        addressService.save(property.getAddress())
        return repository.save(property)
    }

    override fun findById(id: Long) : Property {
        return repository.findById(id).orElseThrow()
    }

    override fun delete(property: Long) = repository.delete(findById(property))
}
