package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import org.springframework.stereotype.Service

@Service
class UserRoleServiceImpl(
    val userRoleRepository: UserRoleRepository
) : UserRoleService {

    override fun getDefault(): UserRole {
        val defaultSlug = "default_role"
        return userRoleRepository.findFirstBySlug(defaultSlug)
            .orElseGet { userRoleRepository.save(UserRole.DEFAULT) }
    }

}