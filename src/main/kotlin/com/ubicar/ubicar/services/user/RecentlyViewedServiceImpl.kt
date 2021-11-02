package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.RecentlyViewed
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.user.RecentlyViewedRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RecentlyViewedServiceImpl(
  private val recentlyViewedRepository: RecentlyViewedRepository
) : RecentlyViewedService {

  override fun findByUserId(id: String): List<Property> {
    val viewed = recentlyViewedRepository.findByUser(id)
    return viewed?.properties ?: listOf()
  }

  override fun addRecentlyViewed(property: Property, user: User) {
    val recent = recentlyViewedRepository.findByUser(user.id)!!
    var exists = false
    recent.properties.map { if (it.id == property.id) exists = true }
    if (!exists) {
      var newRecentList = mutableListOf(property)
      newRecentList.addAll(recent.properties)
      // Para extender la cantidad guardada, hay que cambiar ese 50
      newRecentList = newRecentList.stream().limit(50).collect(Collectors.toList())
      recent.properties.addAll(newRecentList)
      recentlyViewedRepository.save(recent)
    }
  }

  override fun save(user: User) {
    recentlyViewedRepository.save(RecentlyViewed(user))
  }

  override fun findLast10ByUser(id: String): MutableList<Property> {
    val list = recentlyViewedRepository.findByUser(id)?.properties
    return if (list?.size!! >= 10) {
      list.subList(list.size - 11, list.size - 1)
    } else list
  }
}
