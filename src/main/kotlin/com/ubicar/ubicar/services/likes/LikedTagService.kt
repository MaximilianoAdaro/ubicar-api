package com.ubicar.ubicar.services.likes

import com.ubicar.ubicar.entities.*

interface LikedTagService {

  fun save(tags: MutableList<Tag>, idProperty: String, idUser: String): LikedTag

  fun findById(idProperty: String, idUser: String): LikedTag

  fun delete(idProperty: String, idUser: String)
}
