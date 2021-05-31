package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CountryRepository: CrudRepository<Country, Long> {
    fun findFirstByName(name: String): Optional<Country>
}
