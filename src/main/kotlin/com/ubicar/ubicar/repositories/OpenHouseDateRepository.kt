package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.OpenHouseDate
import org.springframework.data.repository.CrudRepository

interface OpenHouseDateRepository : CrudRepository<OpenHouseDate, Long> {}
