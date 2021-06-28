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
    val found: Optional<User> = try {
      val user = jwt.principal as UserDetailsImpl
      userRepository.findByEmail(user.email)
    } catch (e: ClassCastException) {
      userRepository.findByEmail(jwt.principal as String)
    }
    return found.orElseThrow { throw NotFoundException("Token user not found") }
  }
}
