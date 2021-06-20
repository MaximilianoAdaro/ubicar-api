package com.ubicar.ubicar.security

import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(@Autowired val userRepository: UserRepository) : UserDetailsService {

  override fun loadUserByUsername(email: String): UserDetails {
    val user: User = userRepository.findByEmail(email)
      .orElseThrow { UsernameNotFoundException("User Not Found with email: $email") }
    return UserDetailsImpl.build(user)
  }
}
