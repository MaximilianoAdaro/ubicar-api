package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.ConstructionMaterial
import org.springframework.data.repository.CrudRepository

interface MaterialRepository: CrudRepository<ConstructionMaterial, String> {}
