package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "filter")
@Entity
class Filter(

  @Enumerated(value = EnumType.STRING)
  var condition: Condition?,

  @Enumerated(value = EnumType.STRING)
  var typeProperty: TypeOfProperty?,

  @ManyToOne(cascade = [CascadeType.ALL])
  var style: Style?,

  var minPrice: Double?,
  var maxPrice: Double?,
  var minAmountRoom: Int?,
  var maxAmountRoom: Int?,
  var minAmountBathroom: Int?,
  var maxAmountBathroom: Int?,
  var minAmountSquareMeter: Int?,
  var maxAmountSquareMeter: Int?,
  var containsYard: Boolean?,
  var minDistanceSchool: Double?,
  var maxDistanceSchool: Double?,
  var minDistanceUniversity: Double?,
  var maxDistanceUniversity: Double?,
  var minDistanceHospital: Double?,
  var maxDistanceHospital: Double?,
  var minDistancePenitentiary: Double?,
  var maxDistanceCommissary: Double?,
  var minDistanceFireStation: Double?,
  var maxDistanceFireStation: Double?,
  var minDistanceSubway: Double?,
  var maxDistanceSubway: Double?,

  @ManyToOne(cascade = [CascadeType.ALL])
  var user: User?,

  var location: String?

) : AbstractEntity()
