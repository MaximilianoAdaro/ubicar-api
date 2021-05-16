package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.repositories.CountryRepository
import com.ubicar.ubicar.repositories.StateRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StateLoader(private val stateRepository: StateRepository, private val countryRepository: CountryRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val country: Country = countryRepository.findById(50).get()
        val states: MutableList<State> = mutableListOf()
        states.add(State(51, "Buenos Aires", country))
        states.add(State(52, "Córdoba", country))
        states.add(State(53, "Neuquén", country))
        states.add(State(54, "Santa Fé", country))
        states.add(State(55, "Entre Rios", country))
        states.add(State(56, "Catamarca", country))

        states.map { stateRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 5
    }
}
