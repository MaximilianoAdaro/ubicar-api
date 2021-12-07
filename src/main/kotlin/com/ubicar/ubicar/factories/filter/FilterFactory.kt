package com.ubicar.ubicar.factories.filter

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.services.user.UserService
import org.springframework.stereotype.Component

@Component
class FilterFactory(
  private val styleRepository: StyleRepository,
  private val userService: UserService
) : AbstractFactory<Filter, PropertyFilterDto> {

  override fun convert(input: Filter): PropertyFilterDto {
    return PropertyFilterDto(
      input.condition,
      input.typeProperty,
      input.style?.id,
      input.minPrice,
      input.maxPrice,
      input.minAmountRoom,
      input.maxAmountRoom,
      input.minAmountBathroom,
      input.maxAmountBathroom,
      input.minAmountSquareMeter,
      input.maxAmountSquareMeter,
      input.containsYard,
      input.minDistanceSchool,
      input.maxDistanceSchool,
      input.minDistanceUniversity,
      input.maxDistanceUniversity,
      input.minDistanceHospital,
      input.maxDistanceHospital,
      input.minDistancePenitentiary,
      input.maxDistanceCommissary,
      input.minDistanceFireStation,
      input.maxDistanceFireStation,
      input.minDistanceSubway,
      input.maxDistanceSubway,
      input.location
    )
  }

  fun from(filterDto: PropertyFilterDto): Filter {
    val style = if (filterDto.style.isNullOrBlank()) null else styleRepository.findFirstByLabel(filterDto.style!!).orElseThrow()
    return Filter(
      filterDto.condition,
      filterDto.typeProperty,
      style,
      filterDto.minPrice,
      filterDto.maxPrice,
      filterDto.minAmountRoom,
      filterDto.maxAmountRoom,
      filterDto.minAmountBathroom,
      filterDto.maxAmountBathroom,
      filterDto.minAmountSquareMeter,
      filterDto.maxAmountSquareMeter,
      filterDto.containsYard,
      filterDto.minDistanceSchool,
      filterDto.maxDistanceSchool,
      filterDto.minDistanceUniversity,
      filterDto.maxDistanceUniversity,
      filterDto.minDistanceHospital,
      filterDto.maxDistanceHospital,
      filterDto.minDistancePenitentiary,
      filterDto.maxDistanceCommissary,
      filterDto.minDistanceFireStation,
      filterDto.maxDistanceFireStation,
      filterDto.minDistanceSubway,
      filterDto.maxDistanceSubway,
      userService.findLogged(),
      filterDto.location
    )
  }
}
