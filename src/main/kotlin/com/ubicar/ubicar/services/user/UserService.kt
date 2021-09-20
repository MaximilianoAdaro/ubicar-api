package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import java.util.Optional

interface UserService {

  fun findAll(): List<User>

  fun saveUser(userCreationDto: UserCreationDTO): User

  fun saveUserWithGoogle(userCreationDto: GoogleLoginUserDTO): User

  fun findById(id: String): User

  fun findByEmail(email: String): Optional<User>

  fun delete(id: String)

  fun existsByEmail(email: String): Boolean

  fun checkPassword(password: String, user: User): Boolean

  fun findLogged(): User

  fun update(id: String, user: UserDTO): User

  fun registerInspector(userCreation: UserCreationDTO): User

  fun findInspector(property: Property): User?
}
