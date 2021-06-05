package com.ubicar.ubicar.repositories.user

import com.ubicar.ubicar.entities.Permission
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : CrudRepository<Permission, String> {

    override fun findAll(): List<Permission>

}
