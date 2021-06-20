package com.ubicar.ubicar.repositories.user

import com.ubicar.ubicar.entities.UserRole
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRoleRepository : CrudRepository<UserRole, String> {

  override fun findAll(): List<UserRole>

  fun findFirstBySlug(slug: String): Optional<UserRole>
}
