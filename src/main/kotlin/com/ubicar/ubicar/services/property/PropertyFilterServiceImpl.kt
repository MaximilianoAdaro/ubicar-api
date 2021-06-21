package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.repositories.property.filter.PropertyFilterOperationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PropertyFilterServiceImpl(
  private val propertyFilterRepository: PropertyFilterOperationRepository
) : PropertyFilterService {

  override fun filterEvaluationsPaginated(
    filter: PropertyFilterDto,
    pageRequest: PageRequest,
    params: PropertyLazyTableDto,
    orderList: List<String>
  ): Page<Property> {
    return propertyFilterRepository.getFilteredProperties(filter, pageRequest, params, orderList)
  }
}
