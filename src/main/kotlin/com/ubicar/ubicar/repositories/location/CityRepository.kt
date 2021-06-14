package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.City
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CityRepository: CrudRepository<City, String> {

    fun findByName(name: String): Optional<City>
}
