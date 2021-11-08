package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property

interface CsvPropertyService {

  fun createCsvFromProperty(property: Property): String

  fun createCsvFromProperty(propertyId: String)
}
