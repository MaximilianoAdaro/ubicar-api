package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.Tags
import com.ubicar.ubicar.entities.TagsLiked
import com.ubicar.ubicar.entities.User

interface TagsLikedService {

  fun create(property: Property, user: User): TagsLiked

  fun findById(id: String): TagsLiked

  fun findByPropertyIdAndUserId(idProperty: String, idUser: String): TagsLiked?

  fun update(idProperty: String, idUser: String, list: List<String>): TagsLiked

  fun delete(idProperty: String, idUser: String)
}
