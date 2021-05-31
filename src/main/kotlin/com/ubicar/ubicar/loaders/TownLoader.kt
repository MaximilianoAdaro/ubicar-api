package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.Town
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.TownRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class TownLoader(private val townRepository: TownRepository, private val cityRepository: CityRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val city: City = cityRepository.findById(57).get()
        val towns: MutableList<Town> = mutableListOf()
        towns.add(Town(62, "Belgrano", city))
        towns.add(Town(63, "Caballito", city))
        towns.add(Town(64, "Colegiales", city))
        towns.add(Town(65, "Recoleta", city))
        towns.add(Town(66, "Flores", city))
        towns.add(Town(67, "Retiro", city))

        towns.map { townRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 7
    }
}
