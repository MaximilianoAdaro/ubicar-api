package com.ubicar.ubicar.entities

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    var id: String = ""
)
