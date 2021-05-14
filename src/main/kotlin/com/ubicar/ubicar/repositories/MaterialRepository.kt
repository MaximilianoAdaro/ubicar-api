package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.ConstructionMaterial
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialRepository : CrudRepository<ConstructionMaterial, Long> {

    @Query(value = "select a from ConstructionMaterial a")
    override fun findAll() : List<ConstructionMaterial>
}
