package com.ubicar.ubicar.repositories.geoData

import com.ubicar.ubicar.entities.GeoDataProperty
import com.ubicar.ubicar.entities.Property
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface GeoDataPropertyRepository : CrudRepository<GeoDataProperty, String> {}
