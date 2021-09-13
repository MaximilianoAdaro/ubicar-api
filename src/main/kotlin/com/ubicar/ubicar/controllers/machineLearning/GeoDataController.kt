package com.ubicar.ubicar.controllers.machineLearning

import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/geo-data")
class GeoDataController(
  private val geoSpatialService: GeoSpatialService
) {

  @PostMapping("/property/update")
  fun updateGeoPropertyData() {
    geoSpatialService.runAllUpdates()
  }
}
