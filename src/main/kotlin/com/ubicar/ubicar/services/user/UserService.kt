package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.User
import java.util.*

interface UserService {

    fun findAll() : List<User>

    fun save(user: User) : User

    fun findById(id: Long) : User

    fun findByEmail(email: String): Optional<User>

    fun delete(user: Long)

    fun existsByEmail(email: String): Boolean

    fun checkPassword(password: String, user: User): Boolean
}
