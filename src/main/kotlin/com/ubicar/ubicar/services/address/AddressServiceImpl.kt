package com.ubicar.ubicar.services.address

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.repositories.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(private val repository: AddressRepository): AddressService {

    override fun save(address: Address): Address {
        return repository.save(address)
    }

    override fun findById(id: Long): Address {
        return repository.findById(id).orElseThrow()
    }

    override fun delete(address: Long) {
        repository.delete(findById(address))
    }
}
