package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.SecurityMeasure
import org.springframework.data.repository.CrudRepository

interface SecurityRepository : CrudRepository<SecurityMeasure, String>
