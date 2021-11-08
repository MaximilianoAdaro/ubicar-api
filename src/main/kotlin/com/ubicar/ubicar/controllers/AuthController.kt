package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.*
import com.ubicar.ubicar.factories.user.RoleFactory
import com.ubicar.ubicar.factories.user.UserDtoFactory
import com.ubicar.ubicar.factories.user.UserDtoProfileFactory
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.services.auth.AuthenticationService
import com.ubicar.ubicar.services.user.UserService
import javassist.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class AuthController(
  private val userService: UserService,
  private val authenticationService: AuthenticationService,
  private val userDtoFactory: UserDtoFactory,
  private val userDtoProfileFactory: UserDtoProfileFactory,
  private val userRoleRepository: UserRoleRepository,
  private val roleFactory: RoleFactory
) {

  @PostMapping("/login")
  fun login(@RequestBody logInUser: LogInUserDTO, response: HttpServletResponse): UserDTO {
    return userDtoFactory.convert(authenticationService.login(logInUser, response))
  }

  @PostMapping("/google-login")
  fun loginWithGoogle(
    @RequestBody logInUser: GoogleLoginUserDTO,
    response: HttpServletResponse,
    @RequestHeader(name = "Authorization") token: String
  ): UserDTO {
    return userDtoFactory.convert(authenticationService.loginGoogle(logInUser, response, token))
  }

  @PostMapping("/register")
  fun register(@RequestBody userCreation: UserCreationDTO): UserDTO {
    return userDtoFactory.convert(userService.saveUser(userCreation))
  }

  @GetMapping("/me")
  fun getLogged(): UserDTO {
    val authentication: Authentication = SecurityContextHolder.getContext().authentication
    return userDtoFactory.convert(
      userService.findByEmail(authentication.name).orElseThrow { NotFoundException("User not found") }
    )
  }

  @GetMapping("/profile")
  fun getProfileUser(): UserDTOProfile {
    val authentication: Authentication = SecurityContextHolder.getContext().authentication
    return userDtoProfileFactory.convert(
      userService.findByEmail(authentication.name).orElseThrow { NotFoundException("User not found") }
    )
  }

  @GetMapping("/roles")
  fun getRoles(): List<RoleDTO> {
    return userRoleRepository.findAll().map { roleFactory.convert(it) }
  }

  @PostMapping("/logout")
  fun logOut(response: HttpServletResponse): ResponseEntity<Unit> {
    response.addHeader(
      "Set-Cookie",
      "jwt=deleted; httpOnly; SameSite=strict; Path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT"
    )
    response.addHeader(
      "Set-Cookie",
      "google-auth=deleted; httpOnly; SameSite=strict; Path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT"
    )
    return ResponseEntity.noContent().build()
  }
}
