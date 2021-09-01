package com.ubicar.ubicar.dtos.filter

import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.TypeOfProperty
import org.springframework.data.domain.Sort

// - Condición
// - Precio
// - Número de habitaciones
// - Tipo de propiedad (Casa, Depto, etc.)
// - Estilo
// - Número de baños
// - Metros cuadrados
// - Jardín (Si o No)

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
  var stateId: String?,
  var cityId: String?,
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
