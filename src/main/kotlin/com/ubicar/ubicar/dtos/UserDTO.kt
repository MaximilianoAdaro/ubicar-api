package com.ubicar.ubicar.dtos

import java.time.LocalDate

data class UserDTO(
    var id: String,
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
    var password: String,
    var userRole: String
)

data class UserRoleDto(
    var id: String,
    var title: String,
    var slug: String,
    var description: String,
    var active: Boolean = true,
    var creationDate: LocalDate,
)

data class RoleDTO(
    var id: String,
    var title: String
)

