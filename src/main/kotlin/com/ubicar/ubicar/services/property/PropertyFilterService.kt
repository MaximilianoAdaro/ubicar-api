package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PropertyFilterService {

  fun filterPropertiesPaginated(
    filter: PropertyFilterDto,
    pageRequest: PageRequest,
    params: PropertyLazyTableDto,
    orderList: List<String>,
    polygon: Polygon
  ): Page<Property>

  fun filterPropertiesViewBox(filter: PropertyFilterDto, polygon: Polygon?): List<String>

  fun filterProperties(filter: PropertyFilterDto): List<Property>

  fun filterPropertiesForRecommendations(filter: PropertyFilterDto): List<Property>

  fun findByUser(user: User): List<Filter>

  fun checkFilters(property: Property, user: User): Filter?
}
