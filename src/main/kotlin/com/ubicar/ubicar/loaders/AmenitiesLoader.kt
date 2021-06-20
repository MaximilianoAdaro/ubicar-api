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
    amenities.add(Amenity("Balcón"))
    amenities.add(Amenity("Comedor"))
    amenities.add(Amenity("Dormitorio en Suite"))
    amenities.add(Amenity("Baulera"))
    amenities.add(Amenity("Comedor Diario"))
    amenities.add(Amenity("Escritorio"))
    amenities.add(Amenity("Cocina"))
    amenities.add(Amenity("Hall"))
    amenities.add(Amenity("Jardín"))
    amenities.add(Amenity("Lavadero"))
    amenities.add(Amenity("Living"))
    amenities.add(Amenity("Living Comedor"))
    amenities.add(Amenity("Patio"))
    amenities.add(Amenity("Terraza"))
    amenities.add(Amenity("Aire Acondicionado"))
    amenities.add(Amenity("Calefacción"))
    amenities.add(Amenity("Hidromasaje"))
    amenities.add(Amenity("Piscina"))
    amenities.add(Amenity("Sauna"))
    amenities.add(Amenity("Cancha de Deportes"))
    amenities.add(Amenity("Laundry"))
    amenities.add(Amenity("Quincho"))
    amenities.add(Amenity("Solarium"))
    amenities.add(Amenity("Cochera"))
    amenities.add(Amenity("Amoblado"))
    amenities.add(Amenity("Gimnasio"))
    amenities.add(Amenity("Parrilla"))
    amenities.add(Amenity("Sala de Juegos"))
    amenities.add(Amenity("SUM"))
    amenities.add(Amenity("Agua Corriente"))
    amenities.add(Amenity("Desagüe Cloacal"))
    amenities.add(Amenity("Video Cable"))
    amenities.add(Amenity("Gas Natural"))
    amenities.add(Amenity("Internet"))

    amenities.map { amenityService.save(it) }
  }

  override fun getOrder(): Int {
    return 0
  }
}
