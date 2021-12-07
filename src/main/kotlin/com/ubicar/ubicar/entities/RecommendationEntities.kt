package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Table(name = "recommendation")
@Entity
class Recommendation(

  @ManyToMany
  @JoinTable(
    name = "recommendation_property",
    joinColumns = [JoinColumn(name = "recommendation_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var properties: MutableList<Property> = mutableListOf(),

  @OneToOne
  var filter: Filter

) : AbstractEntity()
