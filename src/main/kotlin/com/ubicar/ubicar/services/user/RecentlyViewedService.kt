package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User

interface RecentlyViewedService {

  fun findByUserId(id: String): List<Property>

  fun addRecentlyViewed(property: Property, id: String)

  fun save(user: User)

  fun findFirst10ByUser(id: String): MutableList<Property>
}
