package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import java.time.LocalDate

@Profile("!test")
@Component
class RoleLoader(private val userRoleRepository: UserRoleRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val roles: MutableList<UserRole> = mutableListOf()
        roles.add(UserRole("Comprador/Vendedor", "comprador_vendedor", "comprador_vendedor", true, LocalDate.now(), mutableListOf()))
        roles.add(UserRole("Inspector", "inspector", "inspector", true, LocalDate.now(), mutableListOf()))
        roles.add(UserRole("Inversor", "inversor", "inversor", true, LocalDate.now(), mutableListOf()))
        roles.add(UserRole("Inmobiliaria", "inmobiliaria", "inmobiliaria", true, LocalDate.now(), mutableListOf()))
        roles.map { userRoleRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 8
    }
}
