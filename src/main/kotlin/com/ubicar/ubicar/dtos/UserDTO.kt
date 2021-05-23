package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.User

data class UserDTO(
    val id: Long,
    val email: String
)

data class GoogleLoginUserDTO(
    val name: String,
    val email: String
) {
    fun render() = User(0, email, "password")
}

data class LogInUserDTO(
    val email: String,
    val password: String
) {
    fun render() = User(0, email, password)
}

data class CreateUserDTO(
    val email: String,
    val password: String
) {
    fun render() = User(0, email, password)
}
