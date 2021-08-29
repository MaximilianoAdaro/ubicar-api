package com.ubicar.ubicar.entities.geoSpatialEntities

import com.ubicar.ubicar.entities.AbstractGeomEntity
import com.vividsolutions.jts.geom.MultiLineString
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import javax.persistence.Table

@MappedSuperclass
abstract class MultiLineStringGeomData(
  @Column(unique = true)
  var gid: Int = 0,
  var entidad: Double? = 0.0,
  var objeto: String? = null,
  var fna: String? = null,
  var gna: String? = null,
  var nam: String? = null,
  var fdc: String? = null,
  var sag: String? = null,
  var rgc: Double? = 0.0,
  var ltn: Double? = 0.0,
  var loc: Double? = 0.0,
  var geom: MultiLineString? = null
) : AbstractGeomEntity()

@Table(name = "multi_line_string_railway")
@Entity
class Railway : MultiLineStringGeomData()
