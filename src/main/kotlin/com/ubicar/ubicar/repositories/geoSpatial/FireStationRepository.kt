package com.ubicar.ubicar.repositories.geoSpatial

import com.ubicar.ubicar.entities.geoSpatialEntities.FireStation
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
}
