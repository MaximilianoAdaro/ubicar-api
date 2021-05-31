package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.security.JwtUtils
import com.ubicar.ubicar.services.user.UserService
import javassist.NotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class AuthenticationServiceImpl(private val userService: UserService,
                                private val jwtUtils: JwtUtils,
                                private val authenticationManager: AuthenticationManager): AuthenticationService {

    override fun login(user: User, response: HttpServletResponse): User {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        response.addHeader("Set-Cookie", "jwt=$jwt; HttpOnly; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=false; httpOnly; SameSite=strict;")

        return userService.findByEmail(authentication.name).orElseThrow{NotFoundException("User not found")}
    }

    override fun loginGoogle(logInUser: User, response: HttpServletResponse, token: String): User {
        val user: User = if (userService.findByEmail(logInUser.getEmail()).isPresent) {
            userService.findByEmail(logInUser.getEmail()).get()
        } else {
            userService.save(logInUser)
        }
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(logInUser.getEmail(), "password")
        )
        SecurityContextHolder.getContext().authentication = authentication
        response.addHeader("Set-Cookie", "jwt=$token; httpOnly; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=true; httpOnly; SameSite=strict;")
        return user
    }
}
