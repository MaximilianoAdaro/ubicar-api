package com.ubicar.ubicar.services.filter

import com.ubicar.ubicar.entities.Filter

interface FilterService {

  fun save(filter: Filter): Filter

  fun findByUser(id: String): List<Filter>
}
