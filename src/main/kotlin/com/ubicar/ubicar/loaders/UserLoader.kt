package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.UserOrigin
import com.ubicar.ubicar.repositories.user.UserRepository
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate

@Profile("!test")
@Component
class UserLoader(
  private val userRepository: UserRepository,
  private val userRoleRepository: UserRoleRepository
) : CommandLineRunner, Ordered {

  private val passwordEncoder = BCryptPasswordEncoder()

  override fun run(vararg args: String?) {

    val userRole = userRoleRepository.findFirstBySlug("ROLE_comprador_vendedor")
      .orElseThrow { NotFoundException("User Role not found") }
    val user = User(
      "admin",
      "ubicar.austral2021@gmail.com",
      passwordEncoder.encode("admin"),
      UserOrigin.UBICAR,
      userRole,
      LocalDate.now(),
      mutableListOf()
    )
    userRepository.save(user)
  }

  override fun getOrder(): Int {
    return 8
  }
}
