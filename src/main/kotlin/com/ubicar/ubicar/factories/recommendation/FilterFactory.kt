package com.ubicar.ubicar.factories.recommendation

import com.ubicar.ubicar.dtos.RecommendationDTO
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.entities.Recommendation
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.factories.filter.FilterFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.services.user.UserService
import org.springframework.stereotype.Component

@Component
class RecommendationFactory(
  private val propertyFactory: PropertyFactory,
  private val filterFactory: FilterFactory
): AbstractFactory<Recommendation, RecommendationDTO> {

  override fun convert(input: Recommendation): RecommendationDTO {
    return RecommendationDTO(
      input.properties.map{ propertyFactory.convert(it) }.toMutableList(),
      filterFactory.convert(input.filter)
    )
  }
}
