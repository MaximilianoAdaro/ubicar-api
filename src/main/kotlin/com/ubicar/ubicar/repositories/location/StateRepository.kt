package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface StateRepository : CrudRepository<State, String> {
  fun findFirstByNameAndCountry(name: String, country: Country): Optional<State>
}
