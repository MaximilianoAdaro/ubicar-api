package com.ubicar.ubicar.services.predictor

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.geoSpatial.PointFactory
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import com.ubicar.ubicar.services.property.CsvPropertyService
import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.Geometry
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import com.vividsolutions.jts.geom.Point

@Service
class PredictorServiceImpl(private val csvPropertyService: CsvPropertyService,
                           private val geoSpatialService: GeoSpatialService) : PredictorService {

  private val restTemplate = RestTemplate()

  override fun requestPrediction(property: Property): String {
    val toPredict = csvPropertyService.createCsvFromProperty(property)
    val url = "http://localhost:5000/predict"

    val headers = HttpHeaders()
    headers.contentType = MediaType.TEXT_PLAIN

    val entity = HttpEntity(toPredict, headers)

    val response = restTemplate.postForEntity(url, entity, String::class.java)

    return response.body!!
  }

  override fun sendGeodata(coordinates: String): String {
//    val clean = coordinates.substring(1, coordinates.length-2).split(",")
//    val point = PointFactory.createPoint(clean[0].toDouble(), clean[1].toDouble())
//    return geoSpatialService.save(geoSpatialService.getGeodataOfCoordinates(point)).id
    return ""
  }
}
