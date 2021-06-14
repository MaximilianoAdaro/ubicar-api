package com.ubicar.ubicar.loaders
//
//import com.ubicar.ubicar.entities.Country
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Profile
//import org.springframework.core.Ordered
//import org.springframework.stereotype.Component
//
//@Profile("!test")
//@Component
//class CountryLoader(private val countryRepository: CountryRepository): CommandLineRunner, Ordered {
//
//    override fun run(vararg args: String?) {
//        countryRepository.save(Country("Argentina"))
//    }
//
//    override fun getOrder(): Int {
//        return 4
//    }
//}
