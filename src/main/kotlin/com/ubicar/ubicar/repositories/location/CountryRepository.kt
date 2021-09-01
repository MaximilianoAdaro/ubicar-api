package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CountryRepository : CrudRepository<Country, String> {
  fun findFirstByName(name: String): Optional<Country>
}
