package com.ubicar.ubicar.controllers

import com.ubicar.ubicar.dtos.*
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.factories.user.RoleFactory
import com.ubicar.ubicar.factories.user.UserDtoFactory
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.services.auth.AuthenticationService
import com.ubicar.ubicar.services.property.PropertyService
import com.ubicar.ubicar.services.user.RecentlyViewedService
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
class HomePageController(
  private val recentlyViewedService: RecentlyViewedService,
  private val userService: UserService,
  private val propertyFactory: PropertyFactory,
  private val propertyService: PropertyService
) {

  @GetMapping("/recentlyViewed")
  fun getRecentlyViewed(): List<PropertyDTO> {
    return recentlyViewedService.findLast10ByUser(userService.findLogged().id).map { propertyFactory.convert(it) }
  }

  @GetMapping("/mostLiked")
  fun getMostLiked(): List<PropertyDTO> {
    return propertyService.mostLiked().map { propertyFactory.convert(it) }
  }

  @GetMapping("/all-recently-viewed")
  fun getAllRecentlyViewed(): List<PropertyDTO> {
    return recentlyViewedService.findByUserId(userService.findLogged().id).map { propertyFactory.convert(it) }
  }

}
