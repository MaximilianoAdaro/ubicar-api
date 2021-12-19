package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "geo_data_property")
@Entity
class GeoDataProperty(
  var dRailway: Double,
  var dIndustrialArea: Double,
  var dAirport: Double,
  var dEducation: Double,
  var dFireStation: Double,
  var dHealthBuilding: Double,
  var dPenitentiary: Double,
  var dPort: Double,
  var dSecureBuilding: Double,
  var dTrainStation: Double,
  var dUniversity: Double,
  var dSubway: Double
) : AbstractEntity()
