package com.ubicar.ubicar.dtos

data class UserDTO(
    var id: Long,
    var email: String,
    var userName: String
)

data class GoogleLoginUserDTO(
    var name: String,
    var email: String
)

data class LogInUserDTO(
    var email: String,
    var password: String
)

data class UserCreationDTO(
    var email: String,
    var userName: String,
    var password: String
)
