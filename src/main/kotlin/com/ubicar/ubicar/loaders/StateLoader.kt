package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.repositories.location.CountryRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StateLoader(private val stateRepository: StateRepository, private val countryRepository: CountryRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val country: Country = countryRepository.findFirstByName("Argentina").orElseThrow { NotFoundException("Country not found") }
        val states: MutableList<State> = mutableListOf()
        states.add(State("Buenos Aires", country))
        states.add(State("Córdoba", country))
        states.add(State("Neuquén", country))
        states.add(State("Santa Fé", country))
        states.add(State("Entre Rios", country))
        states.add(State("Catamarca", country))

        states.map { stateRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 5
    }
}
