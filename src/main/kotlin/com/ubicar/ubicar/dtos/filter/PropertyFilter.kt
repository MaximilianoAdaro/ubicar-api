package com.ubicar.ubicar.dtos.filter

import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.TypeOfProperty
import org.springframework.data.domain.Sort

data class PropertyFilterDto(
  var condition: Condition?,
  var typeProperty: TypeOfProperty?,
  var style: String?,
  var minPrice: Double?,
  var maxPrice: Double?,
  var minAmountRoom: Int?,
  var maxAmountRoom: Int?,
  var minAmountBathroom: Int?,
  var maxAmountBathroom: Int?,
  var minAmountSquareMeter: Int?,
  var maxAmountSquareMeter: Int?,
  var containsYard: Boolean?,
  var minDistanceSchool: Double?,
  var maxDistanceSchool: Double?,
  var minDistanceUniversity: Double?,
  var maxDistanceUniversity: Double?,
  var minDistanceHospital: Double?,
  var maxDistanceHospital: Double?,
  var minDistancePenitentiary: Double?,
  var maxDistanceCommissary: Double?,
  var minDistanceFireStation: Double?,
  var maxDistanceFireStation: Double?,
  var minDistanceSubway: Double?,
  var maxDistanceSubway: Double?,
  var location: String?
)

enum class PropertySort {
  ID,
  PRICE,
  CREATION_DATE
}

data class PropertyLazyTableDto(
  val page: Int,
  val size: Int,
  val direction: Sort.Direction,
  val property: PropertySort
)
