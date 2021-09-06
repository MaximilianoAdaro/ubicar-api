package com.ubicar.ubicar.loaders.loadersDto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.factories.geoSpatial.PointFactory

@JsonIgnoreProperties(ignoreUnknown = true)
data class StateJson(
  val provincias: List<StateJsonDto>,
)

data class StateJsonDto(
  val centroide: LatLonDto,
  val id: Double,
  val nombre: String
) {
  fun toState(country: Country): State {
    return State(this.nombre, id, PointFactory.createPoint(centroide.lon, centroide.lat), country)
  }
}

data class LatLonDto(
  val lat: Double,
  val lon: Double
)
