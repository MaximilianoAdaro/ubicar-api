package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.Tag
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class TagsLoader @Autowired constructor(
  private val tagRepository: TagRepository
) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val amenities: List<String> = mutableListOf(
      "Baños",
      "Distancia a escuelas",
      "Distancia a transporte",
      "Distribución",
      "Habitaciones",
      "Metros Cuadrados",
      "Precio",
      "Ubicación"
    )

    amenities.forEach {
      tagRepository.findFirstByValue(it)
        .orElseGet { tagRepository.save(Tag(it)) }
    }
  }

  override fun getOrder(): Int {
    return 9
  }
}
