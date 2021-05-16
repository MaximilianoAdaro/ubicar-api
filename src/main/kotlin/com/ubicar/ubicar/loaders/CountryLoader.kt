package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.repositories.CountryRepository
import com.ubicar.ubicar.repositories.SecurityRepository
import com.ubicar.ubicar.repositories.StyleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class CountryLoader(private val countryRepository: CountryRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        countryRepository.save(Country(50, "Argentina"))
    }

    override fun getOrder(): Int {
        return 4
    }
}
