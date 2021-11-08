package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.TagsLiked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TagsLikedRepository : JpaRepository<TagsLiked, String> {

  @Query(value = "select tl from TagsLiked tl where tl.property.id = ?1 and tl.user.id = ?2")
  fun findByPropertyIdAndUserId(idProperty: String, idUser: String): TagsLiked?
}
