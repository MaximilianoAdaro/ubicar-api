package com.ubicar.ubicar.loaders.loadersDto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.Department
import com.ubicar.ubicar.entities.Municipality
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

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProvinceDto(
  val id: Double,
  val nombre: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MunicipalityJson(
  val municipios: List<MunicipalityJsonDto>,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MunicipalityJsonDto(
  val centroide: LatLonDto,
  val id: Double,
  val categoria: String,
  val nombre: String,
  val provincia: ProvinceDto
) {
  fun toMunicipality(state: State): Municipality {
    return Municipality(nombre, id, categoria, PointFactory.createPoint(centroide.lon, centroide.lat), state)
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class DepartmentJson(
  val departamentos: List<DepartmentJsonDto>,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DepartmentJsonDto(
  val centroide: LatLonDto,
  val id: Double,
  val categoria: String,
  val nombre: String,
  val provincia: ProvinceDto
) {
  fun toDepartment(state: State): Department {
    return Department(nombre, id, categoria, PointFactory.createPoint(centroide.lon, centroide.lat), state)
  }
}
