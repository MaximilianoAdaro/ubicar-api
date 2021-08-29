package com.ubicar.ubicar.repositories.geoSpatial

import com.ubicar.ubicar.entities.geoSpatialEntities.Railway
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface RailwayRepository : CrudRepository<Railway, String> {

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from multi_line_string_railway u " +
      "where st_intersects(:#{#polygon}, u.geom)) b",
    nativeQuery = true
  )
  fun findAllInViewBox(polygon: Polygon): List<String>
}
