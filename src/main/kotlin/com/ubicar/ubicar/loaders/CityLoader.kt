package com.ubicar.ubicar.loaders
//
// import com.ubicar.ubicar.entities.City
// import com.ubicar.ubicar.entities.State
// import com.ubicar.ubicar.repositories.location.CityRepository
// import org.springframework.boot.CommandLineRunner
// import org.springframework.context.annotation.Profile
// import org.springframework.core.Ordered
// import org.springframework.stereotype.Component
//
// @Profile("!test")
// @Component
// class CityLoader(private val cityRepository: CityRepository, private val stateRepository: StateRepository): CommandLineRunner, Ordered {
//
//    override fun run(vararg args: String?) {
//        val state: State = stateRepository.findFirstByName("Buenos Aires").get()
//        val cities: MutableList<City> = mutableListOf()
//        cities.add(City("CABA", state))
//        cities.add(City("GBA Norte", state))
//        cities.add(City("GBA Sur", state))
//        cities.add(City("GBA Oeste", state))
//        cities.add(City("Provincia de Buenos Aires", state))
//
//        cities.map { cityRepository.save(it) }
//    }
//
//    override fun getOrder(): Int {
//        return 6
//    }
// }
