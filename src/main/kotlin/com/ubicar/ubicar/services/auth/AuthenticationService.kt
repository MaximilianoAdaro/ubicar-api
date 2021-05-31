package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.dtos.LogInUserDTO
import com.ubicar.ubicar.entities.User
import javax.servlet.http.HttpServletResponse

interface AuthenticationService {

    fun login(userDto: LogInUserDTO, response: HttpServletResponse): User

    fun loginGoogle(logInUser: User, response: HttpServletResponse, token: String): User
}
