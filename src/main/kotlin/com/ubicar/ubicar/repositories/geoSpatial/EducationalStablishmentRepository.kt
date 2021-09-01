package com.ubicar.ubicar.repositories.geoSpatial

import com.ubicar.ubicar.entities.geoSpatialEntities.EducationalEstablishment
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface EducationalStablishmentRepository : CrudRepository<EducationalEstablishment, String> {

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from point_educational_establishment u " +
      "where st_contains(:#{#polygon}, u.geom)) b",
    nativeQuery = true
  )
  fun findAllInViewBox(polygon: Polygon): List<String>
}
