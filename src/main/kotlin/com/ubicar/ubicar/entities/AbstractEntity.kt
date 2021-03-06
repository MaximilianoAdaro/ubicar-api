package com.ubicar.ubicar.entities

import org.hibernate.annotations.GenericGenerator
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity(
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  var id: String = ""
)

@MappedSuperclass
abstract class AbstractGeomEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: String = ""
)
