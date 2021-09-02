package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Property
import com.vividsolutions.jts.geom.Polygon
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PropertyRepository : JpaRepository<Property, String> {

  @Query(value = "select p from Property p where p.step = 7")
  override fun findAll(pageable: Pageable): Page<Property>

  @Query(value = "select p from Property p where p.owner.id = :#{#id}")
  fun findByOwnerId(id: String): List<Property>

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from property p " +
      "join address a on a.id = p.address_id " +
      "where st_contains(:#{#polygon}, a.coordinates)) b",
    nativeQuery = true
  )
  fun findAllInViewBox(polygon: Polygon): List<String>

  @Query(
    "select * from property p " +
      "join address a on a.id = p.address_id " +
      "where st_contains(:#{#polygon}, a.coordinates)",
    nativeQuery = true
  )
  fun findAllInViewBoxProperty(polygon: Polygon): List<Property>

  @Query("select count(p) from Property p")
  fun totalAmount(): Double

  @Query(
    "select st_asgeojson(b.*) from (" +
      "select * from property p " +
      "join address a on a.id = p.address_id " +
      "where p.id in ?1 " +
      ") b",
    nativeQuery = true
  )
  fun getListAsGeoJsonFeature(filteredProperties: List<String>): List<String>
}
