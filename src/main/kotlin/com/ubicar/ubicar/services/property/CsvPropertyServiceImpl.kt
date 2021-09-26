package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
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

  override fun createCsvFromProperty(property: Property) {
    try {
      val sb = StringBuilder()
      sb.append(this.loadColumns())
      sb.append('\n')
      sb.append(this.propetyData(property))
      sb.append('\n')

      File("/home/maxi/projects/ubicar/ubicar-api/src/main/resources/properties_csv/${property.id}.csv").writeText(sb.toString())
    } catch (e: FileNotFoundException) {
      error("Error message: ${e.message}")
    }
  }

  override fun createCsvFromProperty(propertyId: String) {
    val property = propertyRepository.findById(propertyId)
      .orElseThrow { NotFoundException("Property not found with id $propertyId") }
    createCsvFromProperty(property)
  }

  private fun loadColumns(): String {

    // list of columns for the CSV
    val list = mutableListOf("id", "title", "description")
    list.addAll(spatialColumnsList)

    val joiner = StringJoiner(",")
    list.forEach { joiner.add(it) }
    return joiner.toString()
  }

  private fun propetyData(property: Property): String {
    val runGeoDataUpdate = geoSpatialService.runGeoDataUpdate(property.address!!.coordinates).map { it.toString() }

    // list of the property data columns for the CSV
    val list = mutableListOf(property.id, property.title, property.parkDescription)

    list.addAll(runGeoDataUpdate)
    val joiner = StringJoiner(",")
    list.forEach { joiner.add(it) }
    return joiner.toString()
  }
}
