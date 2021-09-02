package com.ubicar.ubicar.dtos

import com.vividsolutions.jts.geom.Point


data class StateDTO(
  val id: String,
  val name: String
)

data class CityDTO(
  val id: String,
  val name: String
)

data class TownDTO(
  val id: String,
  val name: String
)

enum class CityOrStateDTOType {
  CITY,
  STATE
}

data class StateWithCentroidDTO(
  val id: String,
  val name: String,
  val centroid: CoordinatesDTO,
)

data class CityWithCentroidDTO(
  val id: String,
  val name: String,
  val centroid: CoordinatesDTO,
  val stateName: String
)

data class CityOrStateDTO(
  val type: CityOrStateDTOType,
  val city: CityWithCentroidDTO?,
  val state: StateWithCentroidDTO?
)
