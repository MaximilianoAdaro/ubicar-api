package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto

data class RecommendationDTO(
  var properties: MutableList<PropertyDTO>,
  var filter: PropertyFilterDto
)
