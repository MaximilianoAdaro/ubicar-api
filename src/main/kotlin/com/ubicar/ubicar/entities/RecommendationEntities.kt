package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "recommendation")
@Entity
class Recommendation(

  @OneToMany
  var properties: MutableList<Property> = mutableListOf(),

  @OneToOne
  var filter: Filter

) : AbstractEntity()
