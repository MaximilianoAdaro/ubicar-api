package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.CreateUserDTO
import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.dtos.LogInUserDTO
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.factories.user.UserFactory
import com.ubicar.ubicar.services.auth.AuthenticationService
import javassist.NotFoundException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
class AuthController(private val userService: UserService,
                     private val authenticationService: AuthenticationService) {

    private val userFactory: UserFactory = UserFactory()

    @PostMapping("/login")
    fun login(@RequestBody logInUser: LogInUserDTO, response: HttpServletResponse) : UserDTO {
        return userFactory.convert(authenticationService.login(logInUser, response))
    }

    @PostMapping("/google-login")
    fun loginWithGoogle(@RequestBody logInUser: GoogleLoginUserDTO, response: HttpServletResponse, @RequestHeader(name="Authorization") token: String): UserDTO {
        return userFactory.convert(authenticationService.loginGoogle(logInUser.render(), response, token))
    }

    @PostMapping("/register")
    fun login(@RequestBody user: CreateUserDTO) : UserDTO {
        return userFactory.convert(userService.save(user.render()))
    }

    @GetMapping("/users/me")
    fun getLogged() : UserDTO {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return userFactory.convert(userService.findByEmail(authentication.name).orElseThrow{NotFoundException("User not found")})
    }
}
