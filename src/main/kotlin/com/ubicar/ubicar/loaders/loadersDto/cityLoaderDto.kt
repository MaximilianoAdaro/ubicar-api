package com.ubicar.ubicar.loaders.loadersDto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.factories.geoSpatial.PointFactory

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityJson(
  val localidades: List<CityJsonDto>,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityJsonDto(
  val centroide: LatLonDto,
  val id: Double,
  val nombre: String,
  val provincia: ProvinceDto
) {
  fun toCity(state: State): City {
    return City(this.nombre, id, PointFactory.createPoint(centroide.lon, centroide.lat), state)
  }
}

data class ProvinceDto(
  val id: Double,
  val nombre: String
)
