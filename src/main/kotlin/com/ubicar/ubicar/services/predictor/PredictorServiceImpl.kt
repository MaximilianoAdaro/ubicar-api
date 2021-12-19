package com.ubicar.ubicar.services.predictor

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.services.property.CsvPropertyService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PredictorServiceImpl(
  private val csvPropertyService: CsvPropertyService
) : PredictorService {

  private val restTemplate = RestTemplate()

  override fun requestPrediction(property: Property): String {
    val toPredict = csvPropertyService.createCsvFromProperty(property)
    val url = "http://localhost:5000/predict"
    println("toPredict $toPredict")
    val headers = HttpHeaders()
    headers.contentType = MediaType.TEXT_PLAIN

    val toPredictEncoded = toPredict.toByteArray(Charsets.UTF_8)
    println("toPredictEncoded $toPredictEncoded")
    val entity = HttpEntity(toPredictEncoded, headers)

    val response = restTemplate.postForEntity(url, entity, String::class.java)
    println("response $response")
    return response.body!!
  }

  override fun sendGeodata(coordinates: String): String {
//    val clean = coordinates.substring(1, coordinates.length-2).split(",")
//    val point = PointFactory.createPoint(clean[0].toDouble(), clean[1].toDouble())
//    return geoSpatialService.save(geoSpatialService.getGeodataOfCoordinates(point)).id
    return ""
  }
}
