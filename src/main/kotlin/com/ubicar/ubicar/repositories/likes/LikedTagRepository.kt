package com.ubicar.ubicar.repositories.likes

import com.ubicar.ubicar.entities.LikedTag
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface LikedTagRepository : CrudRepository<LikedTag, String> {

  @Query(value = "select lt from LikedTag lt where lt.user.id = :#{#idUser} and lt.property.id = :#{#idProperty} ")
  fun findById(idProperty: String, idUser: String): LikedTag
}
