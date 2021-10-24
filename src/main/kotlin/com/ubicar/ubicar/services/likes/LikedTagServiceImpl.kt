package com.ubicar.ubicar.services.likes

import com.ubicar.ubicar.entities.*
import com.ubicar.ubicar.repositories.likes.LikedTagRepository
import com.ubicar.ubicar.services.property.PropertyService
import com.ubicar.ubicar.services.user.UserService
import org.springframework.stereotype.Service

@Service
class LikedTagServiceImpl(
  private val propertyService: PropertyService,
  private val userService: UserService,
  private val likedTagRepository: LikedTagRepository
) : LikedTagService {

  override fun save(tags: MutableList<Tag>, idProperty: String, idUser: String): LikedTag {
    return likedTagRepository.save(LikedTag(tags, userService.findById(idUser), propertyService.findById(idProperty)))
  }

  override fun findById(idProperty: String, idUser: String): LikedTag {
    return likedTagRepository.findById(idProperty, idUser)
  }

  override fun delete(idProperty: String, idUser: String) {
    likedTagRepository.delete(findById(idProperty, idUser))
  }
}
