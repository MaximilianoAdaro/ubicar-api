package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.State
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StateRepository : CrudRepository<State, Long> {

    @Query(value = "select a from State")
    override fun findAll() : List<State>
}
