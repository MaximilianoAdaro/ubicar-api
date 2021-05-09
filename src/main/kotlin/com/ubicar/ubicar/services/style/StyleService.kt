package com.ubicar.ubicar.services.style

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.Style

interface StyleService {

    fun findAll() : List<Style>

    fun save(style: Style) : Style

    fun findById(id: Long) : Style

    fun delete(style: Long)
}
