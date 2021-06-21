package com.ubicar.ubicar.services.user

import com.ubicar.ubicar.entities.UserRole

interface UserRoleService {

  fun getDefault(): UserRole
}
