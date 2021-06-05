package com.ubicar.ubicar.services.openHouseDate

import com.ubicar.ubicar.entities.OpenHouseDate
import com.ubicar.ubicar.repositories.property.OpenHouseDateRepository
import org.springframework.stereotype.Service

@Service
class OpenHouseDateServiceImpl(private val openHouseDateRepository: OpenHouseDateRepository): OpenHouseDateService {

    override fun save(openHouseDate: OpenHouseDate): OpenHouseDate {
        return openHouseDateRepository.save(openHouseDate)
    }

    override fun findById(id: Long): OpenHouseDate {
        return openHouseDateRepository.findById(id).orElseThrow()
    }

    override fun delete(openHouseDate: Long) {
        openHouseDateRepository.delete(findById(openHouseDate))
    }
}
