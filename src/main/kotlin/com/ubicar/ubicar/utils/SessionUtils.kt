package com.ubicar.ubicar.utils

import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.user.UserRepository
import com.ubicar.ubicar.security.UserDetailsImpl
import javassist.NotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class SessionUtils(private val userRepository: UserRepository) {

  fun getTokenUserInformation(): User {
    val jwt = SecurityContextHolder.getContext().authentication
      ?: throw UnauthorizedException("Error while getting session token")
    val user: UserDetailsImpl = jwt.principal as UserDetailsImpl
    val found: Optional<User> = userRepository.findByEmail(user.email)
    return found.orElseThrow { throw NotFoundException("Token user not found") }
  }
}
