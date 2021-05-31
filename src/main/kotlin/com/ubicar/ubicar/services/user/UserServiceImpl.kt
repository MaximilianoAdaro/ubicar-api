package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {

    private val passwordEncoder = BCryptPasswordEncoder()

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun save(user: User): User {
        user.setPassword(passwordEncoder.encode(user.getPassword()))
        return userRepository.save(user)
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id).get()
    }

    override fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    override fun delete(user: Long) {
        userRepository.delete(findById(user))
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun checkPassword(password: String, user: User): Boolean {
        return passwordEncoder.matches(password, user.getPassword())
    }
}
