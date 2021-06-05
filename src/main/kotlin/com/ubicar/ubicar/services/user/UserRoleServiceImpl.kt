package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.utils.BadRequestException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserRoleServiceImpl(
    val userRoleRepository: UserRoleRepository
) : UserRoleService {

    override fun getBySlug(slug: String): UserRole {
        return userRoleRepository.findFirstBySlug(slug).orElseThrow { BadRequestException("Role not found") }
    }

}
