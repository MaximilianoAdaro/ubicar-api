package com.ubicar.ubicar.repositories.geoSpatial

import com.ubicar.ubicar.entities.geoSpatialEntities.FireStation
import com.vividsolutions.jts.geom.Point
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FireStationRepository : CrudRepository<FireStation, String> {

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from point_fire_station u " +
      "where st_contains(:#{#polygon}, u.geom)) b",
    nativeQuery = true
  )
  fun findAllInViewBox(polygon: Polygon): List<String>

  @Query("select min(st_distance(st_transform(st_setsrid(geom, 4236), 3857), st_transform(?1, 3857))) from point_fire_station", nativeQuery = true)
  fun calculateMinDistanceFromCoords(coordinates: Point): Double
}
