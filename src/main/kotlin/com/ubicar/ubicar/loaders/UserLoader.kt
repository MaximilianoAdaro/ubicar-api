package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.UserOrigin
import com.ubicar.ubicar.repositories.user.UserRepository
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.services.user.UserService
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
  private val userService: UserService,
  private val userRepository: UserRepository,
  private val roleRepository: UserRoleRepository
) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {

    val role = roleRepository.findFirstBySlug("ROLE_comprador_vendedor").get()

    userRepository.findByEmail("ubicar.austral2021@gmail.com").orElseGet {
      userService.saveUser(
        UserCreationDTO(
          "ubicar.austral2021@gmail.com",
          "admin",
          "admin",
          role.id,
          LocalDate.now()
        )
      )
    }
  }

  override fun getOrder(): Int {
    return 8
  }
}
