package com.ubicar.ubicar.services.openHouseDate

import com.ubicar.ubicar.entities.OpenHouseDate

interface OpenHouseDateService {
    fun save(openHouseDate: OpenHouseDate) : OpenHouseDate

    fun findById(id: Long) : OpenHouseDate

    fun delete(openHouseDate: Long)
}
