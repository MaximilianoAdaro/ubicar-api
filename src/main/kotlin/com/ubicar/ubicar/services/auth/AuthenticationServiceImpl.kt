package com.ubicar.ubicar.services.auth

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.LogInUserDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.UserOrigin
import com.ubicar.ubicar.security.JwtUtils
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.AlreadyExistsException
import com.ubicar.ubicar.utils.BadRequestException
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

    override fun login(userDto: LogInUserDTO, response: HttpServletResponse): User {
        val user: User = userService.findByEmail(userDto.email).orElseThrow { NotFoundException("User not found") }
        if (user.userOrigin == UserOrigin.GOOGLE) throw BadRequestException("You can't login if you registered with google")
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userDto.email, userDto.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        response.addHeader("Set-Cookie", "jwt=$jwt; HttpOnly; Path=/; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=false; httpOnly; Path=/; SameSite=strict;")
        return user
    }

    override fun loginGoogle(logInUser: GoogleLoginUserDTO, response: HttpServletResponse, token: String): User {
        val user: User = userService.findByEmail(logInUser.email)
            .orElseGet { userService.saveUserWithGoogle(logInUser) }
        if (user.userOrigin == UserOrigin.UBICAR) throw AlreadyExistsException("You already have an account in this app, sign in")
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(logInUser.email, "password")
        )
        SecurityContextHolder.getContext().authentication = authentication
        response.addHeader("Set-Cookie", "jwt=$token; httpOnly; Path=/; SameSite=strict;")
        response.addHeader("Set-Cookie", "google-auth=true; httpOnly; Path=/; SameSite=strict;")
        return user
    }
}
