package com.ubicar.ubicar.dtos

import java.time.LocalDateTime

data class UserContactDto(
  var name: String,
  var email: String,
  var cellphone: String,
  var message: String
)

data class PropertyAssignmentDto(
  var street: String,
  var number: Int,
  var city: String,
  var state: String,
  var date: LocalDateTime,
  var email: String
)
