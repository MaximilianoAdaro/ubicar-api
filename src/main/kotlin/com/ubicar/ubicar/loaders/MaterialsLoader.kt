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
        materials.add(ConstructionMaterial("Ladrillo"))
        materials.add(ConstructionMaterial("Ladrillo Hueco"))
        materials.add(ConstructionMaterial("Cemento"))
        materials.add(ConstructionMaterial("Piedra"))
        materials.add(ConstructionMaterial("Chapa"))
        materials.add(ConstructionMaterial("Madera"))

        materials.map { materialRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 1
    }
}
