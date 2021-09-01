package com.ubicar.ubicar.entities.geoSpatialEntities

import com.ubicar.ubicar.entities.AbstractGeomEntity
import com.vividsolutions.jts.geom.MultiPolygon
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import javax.persistence.Table

@MappedSuperclass
abstract class MultiPolygonGeomData(
  @Column(unique = true)
  var gid: Int = 0,
  var entidad: Double? = 0.0,
  var objeto: String? = null,
  var fna: String? = null,
  var gna: String? = null,
  var nam: String? = null,
  var fdc: String? = null,
  var sag: String? = null,
  var geom: MultiPolygon? = null
) : AbstractGeomEntity()

@Table(name = "multi_polygon_manufacturing_and_processing_area")
@Entity
class ManufacturingAndProcessingArea : MultiPolygonGeomData()
