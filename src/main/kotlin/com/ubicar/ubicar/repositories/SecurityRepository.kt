package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.SecurityMeasure
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SecurityRepository : CrudRepository<SecurityMeasure, Long> {

    @Query(value = "select a from SecurityMeasure a")
    override fun findAll() : List<SecurityMeasure>
}
