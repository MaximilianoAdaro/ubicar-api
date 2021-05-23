package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.entities.User
import javax.servlet.http.HttpServletResponse

interface AuthenticationService {

    fun login(user: User, response: HttpServletResponse): User

    fun loginGoogle(logInUser: User, response: HttpServletResponse, token: String): User
}
