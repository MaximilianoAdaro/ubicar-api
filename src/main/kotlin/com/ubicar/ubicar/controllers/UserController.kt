package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.factories.user.RoleFactory
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
  private val userRoleRepository: UserRoleRepository,
  private val roleFactory: RoleFactory
)
