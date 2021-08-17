package com.ubicar.ubicar.factories.filter

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.entities.Filter
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.UserOrigin
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.services.user.UserService
import org.springframework.stereotype.Component

@Component
class FilterFactory(private val styleRepository: StyleRepository,
                    private val userService: UserService) : AbstractFactory<Filter, PropertyFilterDto> {

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
      input.containsYard
    )
  }

  fun from(filterDto: PropertyFilterDto): Filter {
    return Filter(
      filterDto.condition,
      filterDto.typeProperty,
      styleRepository.findFirstByLabel(filterDto.style!!).get(),
      filterDto.minPrice,
      filterDto.maxPrice,
      filterDto.minAmountRoom,
      filterDto.maxAmountRoom,
      filterDto.minAmountBathroom,
      filterDto.maxAmountBathroom,
      filterDto.minAmountSquareMeter,
      filterDto.maxAmountSquareMeter,
      filterDto.containsYard,
      userService.findLogged()
    )
  }
}
