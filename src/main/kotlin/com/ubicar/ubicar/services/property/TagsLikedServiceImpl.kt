package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.*
import com.ubicar.ubicar.repositories.property.TagsLikedRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.stereotype.Service

@Service
class TagsLikedServiceImpl(
  private val tagsLikedRepository: TagsLikedRepository
) : TagsLikedService {

  override fun create(property: Property, user: User): TagsLiked {
    return tagsLikedRepository.save(TagsLiked(property, user))
  }

  override fun findById(id: String): TagsLiked {
    return tagsLikedRepository.findById(id).orElseThrow { NotFoundException("Liked relation not found") }
  }

  override fun findByPropertyIdAndUserId(idProperty: String, idUser: String): TagsLiked? {
    return tagsLikedRepository.findByPropertyIdAndUserId(idProperty, idUser)
  }

  override fun update(idProperty: String, idUser: String, list: List<String>): TagsLiked {
    val tagsLiked = tagsLikedRepository.findByPropertyIdAndUserId(idProperty, idUser)
    tagsLiked?.tags?.clear()
    tagsLiked?.tags = list.toMutableList()
    return tagsLikedRepository.save(tagsLiked!!)
  }

  override fun delete(idProperty: String, idUser: String) {
    tagsLikedRepository.delete(findByPropertyIdAndUserId(idProperty, idUser)!!)
  }
}
