package com.ubicar.ubicar.services.predictor

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.services.property.CsvPropertyService
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.ResponseEntity
import java.util.*
import kotlin.collections.HashMap


@Service
class PredictorServiceImpl(private val csvPropertyService: CsvPropertyService) : PredictorService {

  private val restTemplate = RestTemplate()

  override fun requestPrediction(property: Property): String {
    val toPredict = csvPropertyService.createCsvFromProperty(property)
    val url = "http://localhost:5000/predict"

    val headers = HttpHeaders()
    headers.contentType = MediaType.TEXT_PLAIN
//    headers.accept = Collections.singletonList(MediaType.APPLICATION_JSON)

    val entity = HttpEntity(toPredict, headers)

    val response = restTemplate.postForEntity(url, entity, String::class.java)

    return response.body!!
  }

}
