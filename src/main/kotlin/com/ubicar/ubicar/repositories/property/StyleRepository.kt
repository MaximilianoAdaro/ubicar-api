package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Style
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface StyleRepository : CrudRepository<Style, String> {
  fun findFirstByLabel(label: String): Optional<Style>
}
