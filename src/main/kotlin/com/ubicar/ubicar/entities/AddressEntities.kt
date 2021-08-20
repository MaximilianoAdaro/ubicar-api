package com.ubicar.ubicar.entities

import com.vividsolutions.jts.geom.Point
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "country")
@Entity
class Country(
  @Column(nullable = false)
  var name: String
) : AbstractEntity()

@Table(name = "state")
@Entity
class State(
  @Column(nullable = false)
  var name: String,

  @Column(nullable = false, unique = true)
  var gid: Double,

  var centroid: Point,

  @ManyToOne
  var country: Country
) : AbstractEntity()

@Table(name = "city")
@Entity
class City(

  @Column(nullable = false)
  var name: String,

  @Column(nullable = false, unique = true)
  var gid: Double,

  var centroid: Point,

  @ManyToOne
  var state: State
) : AbstractEntity()

@Table(name = "address")
@Entity
class Address(
  @ManyToOne
  var city: City,

  @Column(nullable = false)
  var street: String,

  @Column(nullable = false)
  var number: Int,

  var coordinates: Point,
) : AbstractEntity()
