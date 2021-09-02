package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.repositories.property.filter.PropertyFilterOperationRepository
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PropertyFilterServiceImpl(
  private val propertyFilterRepository: PropertyFilterOperationRepository,
  private val propertyRepository: PropertyRepository
) : PropertyFilterService {

  override fun filterPropertiesPaginated(
    filter: PropertyFilterDto,
    pageRequest: PageRequest,
    params: PropertyLazyTableDto,
    orderList: List<String>,
    polygon: Polygon
  ): Page<Property> {
    return propertyFilterRepository.getFilteredPropertiesPaginated(filter, pageRequest, params, orderList, polygon)
  }

  override fun filterPropertiesViewBox(filter: PropertyFilterDto, polygon: Polygon): List<String> {
    val filteredProperties = propertyFilterRepository.getFilteredProperties(filter, polygon)
    val filteredIdProperties = filteredProperties.map { it.id }
    if (filteredIdProperties.isEmpty())
      return listOf()
    return propertyRepository.getListAsGeoJsonFeature(filteredIdProperties)
  }
}
