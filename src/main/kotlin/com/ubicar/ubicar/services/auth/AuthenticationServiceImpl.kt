package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.security.JwtUtils
import com.ubicar.ubicar.services.user.UserService
import javassist.NotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse

@Service
class AuthenticationServiceImpl(
    private val userService: UserService,
    private val jwtUtils: JwtUtils,
    private val authenticationManager: AuthenticationManager
) : AuthenticationService {

    override fun login(user: User, response: HttpServletResponse): User {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(user.email, user.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        response.setHeader("Set-Cookie", "jwt=$jwt; HttpOnly; SameSite=strict;")
        return userService.findByEmail(authentication.name).orElseThrow { NotFoundException("User not found") }
    }

    override fun loginGoogle(logInUser: GoogleLoginUserDTO, response: HttpServletResponse, token: String): User {
        val user: User = userService.findByEmail(logInUser.email).orElseGet { userService.saveUserWithGoogle(logInUser) }
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(user.email, user.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        response.setHeader("Set-Cookie", "jwt=$token; httpOnly; SameSite=strict;")
        return user
    }
}
