package com.ubicar.ubicar.services.filter

import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.repositories.filter.FilterRepository
import org.springframework.stereotype.Service

@Service
class FilterServiceImpl(private val filterRepository: FilterRepository) : FilterService {

  override fun save(filter: Filter): Filter {
    return filterRepository.save(filter)
  }

  override fun findByUser(id: String): List<Filter> {
    return filterRepository.findByUser(id)
  }
}
