package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.repositories.property.MaterialRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class MaterialsLoader(private val materialRepository: MaterialRepository) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val materials: List<String> = mutableListOf(
      "Ladrillo", "Ladrillo Hueco", "Cemento", "Piedra", "Chapa", "Madera"
    )

    materials.forEach {
      materialRepository.findFirstByLabel(it)
        .orElseGet { materialRepository.save(ConstructionMaterial(it)) }
    }
  }

  override fun getOrder(): Int {
    return 1
  }
}
