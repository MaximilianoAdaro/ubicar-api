package com.ubicar.ubicar.entities

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: String = ""
)
