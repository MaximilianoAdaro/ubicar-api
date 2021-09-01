package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.factories.geoSpatial.PointFactory
import com.vividsolutions.jts.geom.Point
import java.time.LocalDate
import java.time.LocalTime

data class CreatePropertyDTO(
  val title: String,
  val price: Int,
  val condition: String,
  val type: String,
  val address: AddressDTO,
  val squareFoot: Int,
  val coveredSquareFoot: Int,
  val levels: Int,
  val constructionDate: Int,
  val style: String,
  val environments: Int,
  val rooms: Int,
  val toilets: Int,
  val fullBaths: Int,
  val expenses: Int,
  val amenities: MutableList<String>,
  val materials: MutableList<String>,
  val security: MutableList<String>,
  val parkDescription: String,
  val links: MutableList<String>,
  val contacts: MutableList<ContactDto>,
  val openHouse: MutableList<OpenHouseDateDto>,
  val comments: String,
  val step: Int
)

data class AddressDTO(
  val stateId: String?,
  val state: String?,
  val cityId: String?,
  val city: String?,
  val street: String,
  val number: Int,
  val coordinates: CoordinatesDTO
)

data class CoordinatesDTO(
  var lat: Double,
  var long: Double
) {
  fun toPoint(): Point {
    return PointFactory.createPoint(long, lat)
  }

  companion object {
    fun from(point: Point): CoordinatesDTO = CoordinatesDTO(point.y, point.x)
  }
}

data class ViewBoxCoordinatesDTO(
  val NE: CoordinatesDTO,
  val SE: CoordinatesDTO,
  val SW: CoordinatesDTO,
  val NW: CoordinatesDTO
) {
  fun toPointList(): List<Point> = listOf(NE.toPoint(), SE.toPoint(), SW.toPoint(), NW.toPoint(), NE.toPoint())
}

data class ViewBoxCoordinatesDTOFloat(
  val p1: Double,
  val p2: Double,
  val p3: Double,
  val p4: Double
) {
  fun toDto(): ViewBoxCoordinatesDTO = ViewBoxCoordinatesDTO(
    CoordinatesDTO(p4, p3), // NE lat long
    CoordinatesDTO(p4, p1), // SE lat long
    CoordinatesDTO(p2, p1), // SW lat long
    CoordinatesDTO(p2, p3) // NW lat long
  )
}

data class PropertyDTO(
  val id: String,
  val title: String,
  val price: Int,
  val condition: Condition,
  val type: TypeOfProperty,
  val address: AddressDTO?,
  val squareFoot: Int?,
  val coveredSquareFoot: Int?,
  val levels: Int?,
  val constructionDate: Int?,
  val style: Style?,
  val environments: Int?,
  val rooms: Int?,
  val toilets: Int?,
  val fullBaths: Int?,
  val expenses: Int?,
  val amenities: MutableList<Amenity>,
  val materials: MutableList<MaterialDTO>,
  val security: MutableList<SecurityDTO>,
  val parkDescription: String?,
  val links: MutableList<String>,
  val contacts: MutableList<ContactDto>,
  val openHouse: MutableList<OpenHouseDateDto>,
  val comments: String,
  val liked: Boolean,
  val step: Int,
  val images: List<String> = listOf(),
)

data class PropertyPreviewDTO(
  val id: String,
  val title: String,
  val price: Int,
  val condition: Condition,
  val type: TypeOfProperty,
  val address: AddressDTO?,
  val squareFoot: Int?,
  val coveredSquareFoot: Int?,
  val rooms: Int?,
  val toilets: Int?,
  val fullBaths: Int?
)

data class ContactDto(
  var label: String,
  var email: String
)

data class OpenHouseDateDto(
  var day: LocalDate,
  var initialTime: LocalTime,
  var finalTime: LocalTime
)
