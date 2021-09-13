package com.ubicar.ubicar.repositories.geoSpatial

import com.ubicar.ubicar.entities.geoSpatialEntities.ManufacturingAndProcessingArea
import com.vividsolutions.jts.geom.Point
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface IndustrialZoneRepository : CrudRepository<ManufacturingAndProcessingArea, String> {

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from multi_polygon_manufacturing_and_processing_area u " +
      "where st_intersects(:#{#polygon}, u.geom)) b",
    nativeQuery = true
  )
  fun findAllInViewBox(polygon: Polygon): List<String>

  @Query("select min(st_distance(st_transform(st_setsrid(geom, 4236), 3857), st_transform(?1, 3857))) from multi_polygon_manufacturing_and_processing_area", nativeQuery = true)
  fun calculateMinDistanceFromCoords(coordinates: Point): Double
}
