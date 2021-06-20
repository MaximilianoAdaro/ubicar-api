package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Address
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository<Address, String> {

  @Query(value = "select a from Address a")
  override fun findAll(): List<Address>
}
