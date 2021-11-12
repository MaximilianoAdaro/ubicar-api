package com.ubicar.ubicar.controllers.publicEndpoints

import com.ubicar.ubicar.dtos.GeoType
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/geospatial")
class GeoSpatialPublicController(
  private val geoSpatialService: GeoSpatialService,
  private val propertyService: PropertyService
) {

  @GetMapping("/viewBox")
  fun getPropertiesViewBox(
    @RequestParam b1: Double,
    @RequestParam b2: Double,
    @RequestParam b3: Double,
    @RequestParam b4: Double,
    @RequestParam geoType: GeoType
  ): List<String> {
    val viewBoxCoordinatesDTO = ViewBoxCoordinatesDTOFloat(b1, b2, b3, b4)
    return geoSpatialService.findAllInViewBox(viewBoxCoordinatesDTO, geoType)
  }

  @GetMapping("/loadGeospatialCrap")
  fun loadGeospatialData() {
    propertyService.findAll().map { geoSpatialService.storeGeodataOfProperty(it) }
  }
}
