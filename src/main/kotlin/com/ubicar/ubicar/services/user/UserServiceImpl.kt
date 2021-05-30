package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.User.Companion.DEFAULT_PASSWORD
import com.ubicar.ubicar.factories.user.UserCreationFactory
import com.ubicar.ubicar.factories.user.UserCreationGoogleFactory
import com.ubicar.ubicar.repositories.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userCreationFactory: UserCreationFactory,
    private val userCreationGoogleFactory: UserCreationGoogleFactory
    ): UserService {

    private val passwordEncoder = BCryptPasswordEncoder()

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun saveUser(userCreationDto: UserCreationDTO): User {
        userCreationDto.password = passwordEncoder.encode(userCreationDto.password)
        return userRepository.save(userCreationFactory.from(userCreationDto))
    }

    override fun saveUserWithGoogle(userCreationDto: GoogleLoginUserDTO): User {
        val from = userCreationGoogleFactory.from(userCreationDto, DEFAULT_PASSWORD)
        return userRepository.save(from)
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
        return passwordEncoder.matches(user.password, password)
    }
}
