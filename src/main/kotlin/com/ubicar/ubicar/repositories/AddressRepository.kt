package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.Address
import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository<Address, Long> {
}
