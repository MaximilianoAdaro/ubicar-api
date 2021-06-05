package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.State
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StateRepository : CrudRepository<State, String> {
    fun findFirstByName(name: String): Optional<State>
}
