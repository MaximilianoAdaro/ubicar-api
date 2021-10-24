package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.RecentlyViewed
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.user.RecentlyViewedRepository
import org.springframework.stereotype.Service

@Service
class RecentlyViewedServiceImpl(
  private val recentlyViewedRepository: RecentlyViewedRepository
) : RecentlyViewedService {

  override fun findByUserId(id: String): List<Property> {
    return recentlyViewedRepository.findByUser(id).properties
  }

  override fun addRecentlyViewed(property: Property, id: String) {
    val recent = recentlyViewedRepository.findByUser(id)
    val newRecentList = mutableListOf(property)
    newRecentList.addAll(recent.properties)
    recent.properties = newRecentList
    recentlyViewedRepository.save(recent)
  }

  override fun save(user: User) {
    recentlyViewedRepository.save(RecentlyViewed(user))
  }

  override fun findFirst10ByUser(id: String): MutableList<Property> {
    return recentlyViewedRepository.findFirst10ByUser(id)
  }
}
