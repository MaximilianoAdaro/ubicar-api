package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.repositories.property.MaterialRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class MaterialsLoader(private val materialRepository: MaterialRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val materials: MutableList<ConstructionMaterial> = mutableListOf()
        materials.add(ConstructionMaterial(34, "Ladrillo"))
        materials.add(ConstructionMaterial(35, "Ladrillo Hueco"))
        materials.add(ConstructionMaterial(36, "Cemento"))
        materials.add(ConstructionMaterial(37, "Piedra"))
        materials.add(ConstructionMaterial(38, "Chapa"))
        materials.add(ConstructionMaterial(39, "Madera"))

        materials.map { materialRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 1
    }
}
