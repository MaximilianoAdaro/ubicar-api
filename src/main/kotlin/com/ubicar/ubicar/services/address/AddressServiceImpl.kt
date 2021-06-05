package com.ubicar.ubicar.services.address

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.repositories.location.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(private val addressRepository: AddressRepository): AddressService {

    override fun findAll(): List<Address> {
        return addressRepository.findAll()
    }

    override fun save(address: Address): Address {
        return addressRepository.save(address)
    }

    override fun findById(id: String): Address {
        return addressRepository.findById(id).orElseThrow()
    }

    override fun delete(id: String) {
        addressRepository.delete(findById(id))
    }
}
