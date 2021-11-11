package com.ubicar.ubicar.services.geoSpatialService

import com.ubicar.ubicar.dtos.GeoType
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.entities.GeoDataProperty
import com.ubicar.ubicar.entities.Property
import com.vividsolutions.jts.geom.Point

interface GeoSpatialService {

  fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat, geoType: GeoType): List<String>
  fun getGeodataOfCoordinates(coordinates: Point): GeoDataProperty
  fun storeGeodataOfProperty(property: Property)
  fun runGeoDataUpdate(coordinates: Point): List<Double>
  fun save(geoDataProperty: GeoDataProperty): GeoDataProperty
}
