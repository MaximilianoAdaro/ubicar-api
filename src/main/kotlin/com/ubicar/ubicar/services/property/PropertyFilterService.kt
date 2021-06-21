package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PropertyFilterService {

  fun filterEvaluationsPaginated(
    filter: PropertyFilterDto,
    pageRequest: PageRequest,
    params: PropertyLazyTableDto,
    orderList: List<String>
  ): Page<Property>
}
