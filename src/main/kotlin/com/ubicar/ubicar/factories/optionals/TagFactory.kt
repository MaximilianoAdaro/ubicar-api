package com.ubicar.ubicar.factories.optionals

import com.ubicar.ubicar.dtos.TagDTO
import com.ubicar.ubicar.entities.Tag
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class TagFactory : AbstractFactory<Tag, TagDTO> {

  override fun convert(input: Tag): TagDTO {
    return TagDTO(input.id, input.value)
  }
}
