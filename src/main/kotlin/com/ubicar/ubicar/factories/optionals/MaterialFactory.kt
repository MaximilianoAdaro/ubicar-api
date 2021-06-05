package com.ubicar.ubicar.factories.optionals

import com.ubicar.ubicar.dtos.MaterialDTO
import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class MaterialFactory: AbstractFactory<ConstructionMaterial, MaterialDTO> {

    override fun convert(input: ConstructionMaterial): MaterialDTO {
        return MaterialDTO(input.id, input.label)
    }
}
