package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.entities.User
import java.util.*

interface UserService {

    fun findAll() : List<User>

    fun saveUser(userCreationDto: UserCreationDTO) : User

    fun saveUserWithGoogle(userCreationDto: GoogleLoginUserDTO) : User

    fun findById(id: String) : User

    fun findByEmail(email: String): Optional<User>

    fun delete(id: String)

    fun existsByEmail(email: String): Boolean

    fun checkPassword(password: String, user: User): Boolean
}
