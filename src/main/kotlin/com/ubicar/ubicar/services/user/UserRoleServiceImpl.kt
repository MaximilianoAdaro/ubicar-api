package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserRoleServiceImpl(
    val userRoleRepository: UserRoleRepository
) : UserRoleService {

    override fun getDefault(): UserRole {
        val defaultSlug = "ROLE_comprador_vendedor"
        return userRoleRepository.findFirstBySlug(defaultSlug)
            .orElseGet { userRoleRepository.save(defaultRole()) }
    }

    private fun defaultRole(): UserRole {
        return UserRole(
            "Comprador/Vendedor", "ROLE_comprador_vendedor", "comprador_vendedor",
            true, LocalDate.now(), mutableListOf()
        )
    }

}
