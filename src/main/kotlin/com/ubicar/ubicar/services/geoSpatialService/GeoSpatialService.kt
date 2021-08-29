package com.ubicar.ubicar.services.geoSpatialService

import com.ubicar.ubicar.dtos.GeoType
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat

interface GeoSpatialService {

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat, geoType: GeoType): List<String>
}
