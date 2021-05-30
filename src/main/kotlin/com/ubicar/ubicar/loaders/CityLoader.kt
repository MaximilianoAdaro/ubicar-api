package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class CityLoader(private val cityRepository: CityRepository, private val stateRepository: StateRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val state: State = stateRepository.findById(51).get()
        val cities: MutableList<City> = mutableListOf()
        cities.add(City(57, "CABA", state))
        cities.add(City(58, "GBA Norte", state))
        cities.add(City(59, "GBA Sur", state))
        cities.add(City(60, "GBA Oeste", state))
        cities.add(City(61, "Provincia de Buenos Aires", state))

        cities.map { cityRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 6
    }
}
