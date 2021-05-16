package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.services.amenity.AmenityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class AmenitiesLoader @Autowired constructor(private val amenityService: AmenityService) : CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val amenities: MutableList<Amenity> = mutableListOf()
        amenities.add(Amenity(0, "Balcón"))
        amenities.add(Amenity(1, "Comedor"))
        amenities.add(Amenity(2, "Dormitorio en Suite"))
        amenities.add(Amenity(3, "Baulera"))
        amenities.add(Amenity(4, "Comedor Diario"))
        amenities.add(Amenity(5, "Escritorio"))
        amenities.add(Amenity(6, "Cocina"))
        amenities.add(Amenity(7, "Hall"))
        amenities.add(Amenity(8, "Jardín"))
        amenities.add(Amenity(9, "Lavadero"))
        amenities.add(Amenity(10, "Living"))
        amenities.add(Amenity(11, "Living Comedor"))
        amenities.add(Amenity(12, "Patio"))
        amenities.add(Amenity(13, "Terraza"))
        amenities.add(Amenity(14, "Aire Acondicionado"))
        amenities.add(Amenity(15, "Calefacción"))
        amenities.add(Amenity(16, "Hidromasaje"))
        amenities.add(Amenity(17, "Piscina"))
        amenities.add(Amenity(18, "Sauna"))
        amenities.add(Amenity(19, "Cancha de Deportes"))
        amenities.add(Amenity(20, "Laundry"))
        amenities.add(Amenity(21, "Quincho"))
        amenities.add(Amenity(22, "Solarium"))
        amenities.add(Amenity(23, "Cochera"))
        amenities.add(Amenity(24, "Amoblado"))
        amenities.add(Amenity(25, "Gimnasio"))
        amenities.add(Amenity(26, "Parrilla"))
        amenities.add(Amenity(27, "Sala de Juegos"))
        amenities.add(Amenity(28, "SUM"))
        amenities.add(Amenity(29, "Agua Corriente"))
        amenities.add(Amenity(30, "Desagüe Cloacal"))
        amenities.add(Amenity(31, "Video Cable"))
        amenities.add(Amenity(32, "Gas Natural"))
        amenities.add(Amenity(33, "Internet"))

        amenities.map { amenityService.save(it) }
    }

    override fun getOrder(): Int {
        return 0
    }
}
