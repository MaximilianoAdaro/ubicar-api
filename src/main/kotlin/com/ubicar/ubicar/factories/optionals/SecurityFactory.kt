package com.ubicar.ubicar.factories.optionals

import com.ubicar.ubicar.dtos.SecurityDTO
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class SecurityFactory : AbstractFactory<SecurityMeasure, SecurityDTO> {

  override fun convert(input: SecurityMeasure): SecurityDTO {
    return SecurityDTO(input.id, input.label)
  }
}
