package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.LogInUserDTO
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.factories.user.UserFactory
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.factories.user.UserDtoFactory
import com.ubicar.ubicar.services.auth.AuthenticationService
import com.ubicar.ubicar.services.auth.FirebaseService
import com.ubicar.ubicar.services.user.UserService
import javassist.NotFoundException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController("/auth")
class AuthController(private val userService: UserService,
                     private val authenticationService: AuthenticationService) {

    private val userDtoFactory: UserDtoFactory = UserDtoFactory()

    @PostMapping("/login")
    fun login(@RequestBody logInUser: LogInUserDTO, response: HttpServletResponse) : UserDTO {
        return userDtoFactory.convert(authenticationService.login(logInUser, response))
    }

    @PostMapping("/google-login")
    fun loginWithGoogle(@RequestBody logInUser: GoogleLoginUserDTO, response: HttpServletResponse, @RequestHeader(name="Authorization") token: String): UserDTO {
        return userDtoFactory.convert(authenticationService.loginGoogle(logInUser, response, token))
    }

    @PostMapping("/register")
    fun login(@RequestBody userCreation: UserCreationDTO) : UserDTO {
        return userDtoFactory.convert(userService.saveUser(userCreation))
    }

    @GetMapping("/me")
    fun getLogged() : UserDTO {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return userDtoFactory.convert(userService.findByEmail(authentication.name).orElseThrow{NotFoundException("User not found")})
    }
}
