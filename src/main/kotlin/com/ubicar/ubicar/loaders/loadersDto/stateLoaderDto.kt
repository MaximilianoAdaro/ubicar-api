package com.ubicar.ubicar.loaders.loadersDto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.State
import org.postgis.Point

@JsonIgnoreProperties(ignoreUnknown = true)
data class StateJson(
  val provincias: List<StateJsonDto>,
)

data class StateJsonDto(
  val centroide: LatLonDto,
  val id: Double,
  val nombre: String
) {
  fun toState(country: Country): State = State(this.nombre, id, Point(centroide.lon, centroide.lat), country)
}

data class LatLonDto(
  val lat: Double,
  val lon: Double
)
