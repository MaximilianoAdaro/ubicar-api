package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.SecurityMeasure
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface SecurityRepository : CrudRepository<SecurityMeasure, String> {
  fun findFirstByLabel(label: String): Optional<SecurityMeasure>
}
