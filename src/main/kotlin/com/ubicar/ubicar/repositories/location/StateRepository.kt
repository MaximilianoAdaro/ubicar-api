package com.ubicar.ubicar.repositories.location

import com.ubicar.ubicar.entities.State
import org.springframework.data.repository.CrudRepository

interface StateRepository: CrudRepository<State, Long> {}
