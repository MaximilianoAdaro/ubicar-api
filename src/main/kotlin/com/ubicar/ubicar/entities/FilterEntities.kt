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

  @ManyToOne(cascade = [CascadeType.ALL])
  var user: User?

) : AbstractEntity()
