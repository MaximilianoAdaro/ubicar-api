package com.ubicar.ubicar.factories.optionals

import com.ubicar.ubicar.dtos.StyleDTO
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.factories.AbstractFactory

class StyleFactory: AbstractFactory<Style, StyleDTO> {

    override fun convert(input: Style): StyleDTO {
        return StyleDTO(input.id, input.label)
    }
}
