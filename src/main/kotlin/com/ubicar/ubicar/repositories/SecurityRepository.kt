package com.ubicar.ubicar.repositories

import com.ubicar.ubicar.entities.SecurityMeasure
import org.springframework.data.repository.CrudRepository

interface SecurityRepository: CrudRepository<SecurityMeasure, Long> {}
