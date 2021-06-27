package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.ConstructionMaterial
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface MaterialRepository : CrudRepository<ConstructionMaterial, String> {
  fun findFirstByLabel(label: String): Optional<ConstructionMaterial>
}
