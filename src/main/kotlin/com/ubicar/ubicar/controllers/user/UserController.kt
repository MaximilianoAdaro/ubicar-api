package com.ubicar.ubicar.controllers.user

import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.factories.user.UserDtoFactory
import com.ubicar.ubicar.services.user.UserService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
  private val userService: UserService,
  private val userDtoFactory: UserDtoFactory
) {

  @PutMapping("/{id}")
  fun editUser(@PathVariable id: String, @RequestBody userDto: UserDTO): UserDTO {
    return userDtoFactory.convert(userService.update(id, userDto))
  }
}
