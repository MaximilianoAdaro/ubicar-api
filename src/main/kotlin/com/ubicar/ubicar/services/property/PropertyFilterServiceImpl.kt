package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.filter.PropertyFilterRepository
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.repositories.property.filter.PropertyFilterOperationRepository
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PropertyFilterServiceImpl(
  private val filterRepository: PropertyFilterOperationRepository,
  private val propertyRepository: PropertyRepository,
  private val propertyFilterRepository: PropertyFilterRepository
) : PropertyFilterService {

  override fun filterPropertiesPaginated(
    filter: PropertyFilterDto,
    pageRequest: PageRequest,
    params: PropertyLazyTableDto,
    orderList: List<String>,
    polygon: Polygon
  ): Page<Property> {
    return filterRepository.getFilteredPropertiesPaginated(filter, pageRequest, params, orderList, polygon)
  }

  override fun filterPropertiesViewBox(filter: PropertyFilterDto, polygon: Polygon?): List<String> {
    val filteredProperties = filterRepository.getFilteredProperties(filter, polygon)
    val filteredIdProperties = filteredProperties.map { it.id }
    if (filteredIdProperties.isEmpty())
      return listOf()
    return propertyRepository.getListAsGeoJsonFeature(filteredIdProperties)
  }

  override fun filterProperties(filter: PropertyFilterDto): List<Property> {
    return filterRepository.getFilteredProperties(filter, null)
  }

  override fun filterPropertiesForRecommendations(filter: PropertyFilterDto): List<Property> {
    return filterRepository.getFilteredPropertiesForRecommendations(filter)
  }

  override fun findByUser(user: User): List<Filter> {
    return propertyFilterRepository.findByUser(user.id)
  }

  private fun findAvailable(user: User): List<Filter> {
    return propertyFilterRepository.findAvailable(user.id).asReversed()
  }

  override fun checkFilters(property: Property, user: User): Filter? {
    return filterRepository.checkFilters(property, findAvailable(user))
  }
}
