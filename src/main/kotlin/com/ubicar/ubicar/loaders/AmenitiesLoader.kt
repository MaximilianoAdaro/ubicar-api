package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.repositories.property.AmenityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class AmenitiesLoader @Autowired constructor(
  private val amenitiesRepository: AmenityRepository
) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val amenities: List<String> = mutableListOf(
      "Balcón",
      "Comedor",
      "Dormitorio en Suite",
      "Baulera",
      "Comedor Diario",
      "Escritorio",
      "Cocina",
      "Hall",
      "Jardín",
      "Lavadero",
      "Living",
      "Living Comedor",
      "Patio",
      "Terraza",
      "Aire Acondicionado",
      "Calefacción",
      "Hidromasaje",
      "Piscina",
      "Sauna",
      "Cancha de Deportes",
      "Laundry",
      "Quincho",
      "Solarium",
      "Cochera",
      "Amoblado",
      "Gimnasio",
      "Parrilla",
      "Sala de Juegos",
      "SUM",
      "Agua Corriente",
      "Desagüe Cloacal",
      "Video Cable",
      "Gas Natural",
      "Internet"
    )

    amenities.forEach {
      amenitiesRepository.findFirstByLabel(it)
        .orElseGet { amenitiesRepository.save(Amenity(it)) }
    }
  }

  override fun getOrder(): Int {
    return 0
  }
}
