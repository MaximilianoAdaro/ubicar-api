package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.LogInUserDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.security.JwtUtils
import com.ubicar.ubicar.services.user.UserService
import javassist.NotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse

@Service
class AuthenticationServiceImpl(
    private val userService: UserService,
    private val jwtUtils: JwtUtils,
    private val authenticationManager: AuthenticationManager
) : AuthenticationService {

    override fun login(userDto: LogInUserDTO, response: HttpServletResponse): User {
        val user: User = verifyUser(userDto)
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userDto.email, userDto.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        response.addHeader("Set-Cookie", "jwt=$jwt; HttpOnly; Path=/; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=false; httpOnly; Path=/; SameSite=strict;")

        return user
    }

    private fun verifyUser(logInUser: LogInUserDTO): User {
        val user: User = userService.findByEmail(logInUser.email).orElseThrow { NotFoundException("User not found") }
        if (!userService.checkPassword(logInUser.password, user))
            throw BadCredentialsException("Password is incorrect")
        return user
    }

    override fun loginGoogle(logInUser: GoogleLoginUserDTO, response: HttpServletResponse, token: String): User {
        val user: User = userService.findByEmail(logInUser.email)
            .orElseGet { userService.saveUserWithGoogle(logInUser) }
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                logInUser.email, "password".plus(logInUser.email.plus("password")),
                listOf(SimpleGrantedAuthority(user.userRole.slug))
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        response.addHeader("Set-Cookie", "jwt=$token; httpOnly; Path=/; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=true; httpOnly; Path=/; SameSite=strict;")
        return user
    }
}
