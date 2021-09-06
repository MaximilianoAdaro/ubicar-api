package com.ubicar.ubicar.entities.geoSpatialEntities

import com.ubicar.ubicar.entities.AbstractGeomEntity
import com.vividsolutions.jts.geom.Point
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import javax.persistence.Table

@MappedSuperclass
abstract class PointGeomData(
  @Column(unique = true)
  var gid: Int = 0,
  var entidad: Double? = 0.0,
  var objeto: String? = null,
  var fna: String? = null,
  var gna: String? = null,
  var nam: String? = null,
  var fdc: String? = null,
  var sag: String? = null,
  var geom: Point? = null
) : AbstractGeomEntity()

@Table(name = "point_airport")
@Entity
class Airport : PointGeomData()

@Table(name = "point_fire_station")
@Entity
class FireStation : PointGeomData()

@Table(name = "point_penitentiary")
@Entity
class Penitentiary : PointGeomData()

@Table(name = "point_security_building")
@Entity
class SecurityBuilding : PointGeomData()

@Table(name = "point_educational_establishment")
@Entity
class EducationalEstablishment : PointGeomData()

@Table(name = "point_health_building")
@Entity
class HealthBuilding : PointGeomData()

@Table(name = "point_train_station")
@Entity
class TrainStation : PointGeomData()

@Table(name = "point_port")
@Entity
class Port : PointGeomData()

@Table(name = "point_university")
@Entity
class University : PointGeomData()
