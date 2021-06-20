package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface CountryRepository : CrudRepository<Country, String> {
  fun findFirstByName(name: String): Optional<Country>
}
