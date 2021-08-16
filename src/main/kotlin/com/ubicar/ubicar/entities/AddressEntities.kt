package com.ubicar.ubicar.entities

import org.postgis.Point
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
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

  @OneToOne(cascade = [CascadeType.ALL])
  var coordinates: Coordinates
) : AbstractEntity()

@Table(name = "coordinates")
@Entity
class Coordinates(
  @Column(nullable = false)
  var lat: Double,
  @Column(nullable = false)
  var long: Double
) : AbstractEntity()
