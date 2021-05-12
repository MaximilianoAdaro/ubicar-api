package com.ubicar.ubicar.services.address

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.repositories.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(private val addressRepository: AddressRepository): AddressService {

    override fun findAll(): List<Address> {
        return addressRepository.findAll()
    }

    override fun save(address: Address): Address {
        return addressRepository.save(address)
    }

    override fun findById(id: Long): Address {
        return addressRepository.findById(id).orElseThrow()
    }

    override fun delete(address: Long) {
        addressRepository.delete(findById(address))
    }
}
