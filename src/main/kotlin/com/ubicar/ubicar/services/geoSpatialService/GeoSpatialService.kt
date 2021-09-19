package com.ubicar.ubicar.services.geoSpatialService

import com.ubicar.ubicar.dtos.GeoType
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.vividsolutions.jts.geom.Point

interface GeoSpatialService {

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat, geoType: GeoType): List<String>
  fun runAllUpdates()
  fun runGeoDataUpdate(coordinates: Point): List<Double>
}
