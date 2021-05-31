package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.Country
import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<Country, Long> {
}
