package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileNotFoundException
import java.util.StringJoiner

@Service
class CsvPropertyServiceImpl(
  private val propertyRepository: PropertyRepository,
  private val geoSpatialService: GeoSpatialService
) : CsvPropertyService {

  val propertyDefaultColumnsList =
    listOf("surface_total", "surface_covered", "rooms", "l2", "l3")

  var spatialColumnsList = listOf(
    "dRailway",
    "dIndustrialArea",
    "dAirport",
    "dEducation",
    "dFireStation",
    "dHealthBuilding",
    "dPenitentiary",
    "dPort",
    "dSecureBuilding",
    "dTrainStation",
    "dUniversity"
  )

  // var propertyTypeColumnsList = listOf("property_type_PH", "property_type_apartment", "property_type_house", "property_type_store")
  var propertyTypeColumnsList = listOf("property_type")

  override fun createCsvFromProperty(property: Property): String {
    val sb = StringBuilder()
    sb.append(this.loadColumns())
    sb.append('\n')
    sb.append(this.propetyData(property))
    sb.append('\n')
    return sb.toString()
  }

  override fun createCsvFromProperty(propertyId: String) {
    val property = propertyRepository.findById(propertyId)
      .orElseThrow { NotFoundException("Property not found with id $propertyId") }
    createCsvFromProperty(property)
  }

  private fun loadColumns(): String {

    // list of columns for the CSV
    val list = mutableListOf<String>()
    list.addAll(propertyDefaultColumnsList)
    list.addAll(spatialColumnsList)
    list.addAll(propertyTypeColumnsList)

    val joiner = StringJoiner(",")
    list.forEach { joiner.add(it) }
    return joiner.toString()
  }

  private fun propetyData(property: Property): String {
    val propertyDefaultResult = this.propertyDefaultResult(property)
    val runGeoDataUpdate = geoSpatialService.runGeoDataUpdate(property.address!!.coordinates).map { it.toString() }
    val propertyTypeResult = this.propertyTypeResult(property)

    // list of the property data columns for the CSV
    val list = mutableListOf<String>()
    list.addAll(propertyDefaultResult)
    list.addAll(runGeoDataUpdate)
    list.add(propertyTypeResult)

    val joiner = StringJoiner(",")
    list.forEach { joiner.add(it) }
    return joiner.toString()
  }

  private fun propertyTypeResult(property: Property): String {
    val type_PH = "ph"
    val type_APARTMENT = "apartment"
    val type_HOUSE = "house"
    val type_STORE = "store"

    return when (property.type) {
      TypeOfProperty.Casa -> type_HOUSE
      TypeOfProperty.Quinta -> type_HOUSE
      TypeOfProperty.Departamento -> type_APARTMENT
      TypeOfProperty.Hotel -> type_APARTMENT
      TypeOfProperty.Compartido -> type_HOUSE
      TypeOfProperty.PH -> type_PH
      TypeOfProperty.Local -> type_STORE
      TypeOfProperty.Edificio -> type_APARTMENT
      TypeOfProperty.Loft -> type_APARTMENT
      TypeOfProperty.Oficina -> type_APARTMENT
      TypeOfProperty.Consultorio -> type_STORE
      TypeOfProperty.Country -> type_HOUSE
      TypeOfProperty.Flat -> type_APARTMENT
      TypeOfProperty.Galpon -> type_STORE
      TypeOfProperty.Terreno -> type_HOUSE
      TypeOfProperty.Cochera -> type_HOUSE
    }
  }

  // "price_aprox_usd", "surface_total_in_m2", "surface_covered_in_m2", "price_usd_per_m2", "rooms"
  private fun propertyDefaultResult(property: Property): List<String> {
    val squareFoot = property.squareFoot
    val l2 = "Capital Federal"
    val l3 = "Palermo"
    // val stateName = property.address!!.city.state.name
    return listOf(
      squareFoot.toString(),
      property.coveredSquareFoot.toString(),
      property.rooms.toString(),
      l2,
      l3
    )
  }
}
